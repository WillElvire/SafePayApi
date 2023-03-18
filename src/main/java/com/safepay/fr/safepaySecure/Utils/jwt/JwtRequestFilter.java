package com.safepay.fr.safepaySecure.Utils.jwt;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Scope("prototype")
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    AUserRepository aUserRepository;
    private JwTokenClient jwTokenClient;

    public JwtRequestFilter(AUserRepository userRepository, JwTokenClient jwTokenClient) {
        this.aUserRepository = userRepository;
        this.jwTokenClient = jwTokenClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        String email = null;
        String jwtToken = null;
        // The JWT token is in the form "bearer token".
        // Remove the carrier word and get only the token
        if (!(header == null) && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
            try {
                email = jwTokenClient.getEmailFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().warn("Unable to get JWT Token [an error occured during getting username from token] :: {}", e.getMessage());
                // throw new JwtException(String.format("Unable to get JWT Token [an error occured during getting username from token] :: %s", e.getMessage()));
            } catch (ExpiredJwtException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().warn("JWT Token is expired and not valid anymore :: {}", e.getMessage());
                // throw new JwtException(String.format("JWT Token is expired and not valid anymore :: %s", e.getMessage()));
            } catch (SignatureException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().warn("Invalid JWT signature :: {}", e.getMessage());
                // throw new JwtException(String.format("Invalid JWT signature :: %s", e.getMessage()));
            } catch (MalformedJwtException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().warn("Invalid JWT token :: {}", e.getMessage());
                // throw new JwtException(String.format("Invalid JWT token :: %s", e.getMessage()));
            } catch (UnsupportedJwtException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().error("JWT token is unsupported :: {}", e.getMessage());
                // throw new JwtException(String.format("JWT token is unsupported :: %s", e.getMessage()));
            } catch (BadCredentialsException e) {
                response.setHeader("Exception", e.getMessage());
                getLogger().error("JWT BadCredentialsException :: {}", e.getMessage());
                // throw new JwtException(String.format("JWT BadCredentialsException :: %s", e.getMessage()));
            }
        } else {
            response.setHeader("Exception", "JWT Token does not begin with Bearer String");
            getLogger().warn("JWT Token does not begin with Bearer String");
        }
        // Once we get the token, validate it.
        if (!(email == null) && SecurityContextHolder.getContext().getAuthentication() == null) {
            getLogger().debug("Security context was null, so authorizing user");
            // UserDetails userDetails = actorService.loadUserByUsername(username);
            MUser mUser = aUserRepository.findByEmail(email);
            // if the token is valid, configure Spring Security to manually set authentication
            if (jwTokenClient.validateToken(jwtToken, mUser)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                mUser,
                                null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After defining the authentication in the context, we specify
                // that the current user is authenticated. So it successfully passes Spring security configurations.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
}
