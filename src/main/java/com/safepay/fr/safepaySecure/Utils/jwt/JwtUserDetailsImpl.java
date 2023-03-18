package com.safepay.fr.safepaySecure.Utils.jwt;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@AllArgsConstructor
@NoArgsConstructor
public class JwtUserDetailsImpl {

    private static final long serialVersionUID = 1L;
    private String id;
    private  String firstname;
    private String lastname;
    private String email;

    public static JwtUserDetailsImpl build(MUser user, HttpServletRequest request) {
        HashMap device = new HashMap<>();
        device.put("userAgent", request.getHeader("user-agent"));
        device.put("remoteAddress", request.getRemoteAddr());
        return new JwtUserDetailsImpl(user.getId(),user.getFirstname(),user.getLastname(), user.getEmail());
    }
}
