package com.safepay.fr.safepaySecure.BML.Payload;

public class MLoginPayload {
    private String email;
    private String password;
    public MLoginPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public MLoginPayload() {

    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
