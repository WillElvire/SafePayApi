package com.safepay.fr.safepaySecure.Utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
@Component
public class JwtAuthentificationEntryPoint  implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        final ReturnMessage body = new ReturnMessage();
        body.setCode(HttpStatus.UNAUTHORIZED);
        body.setMessage(authException.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, body);
        responseStream.flush();
    }
    private org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
}
