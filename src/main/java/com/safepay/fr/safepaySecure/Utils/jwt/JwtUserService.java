package com.safepay.fr.safepaySecure.Utils.jwt;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserService implements UserDetailsService {
    @Autowired
    private AUserRepository userRepository;
    private HttpServletRequest servletRequest;

    public JwtUserService(AUserRepository userRepository, HttpServletRequest servletRequest) {
        this.userRepository = userRepository;
        this.servletRequest = servletRequest;
    }


    @Transactional
    public JwtUserDetailsImpl loadByEmail(String email)  {
        MUser user = userRepository.findByEmail(email);
        ReturnMessage message = new ReturnMessage();
        if (user == null) {
           message.setCode(HttpStatus.BAD_REQUEST);
        }
        return JwtUserDetailsImpl.build(user, servletRequest);
    }

    private org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass().getSimpleName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
