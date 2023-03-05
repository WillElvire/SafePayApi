package com.safepay.fr.safepaySecure.BML.Error;

import org.springframework.http.HttpStatus;


public class ReturnMessage {
    private String message;
    private Object ReturnObject;
    private HttpStatus Code;

    public ReturnMessage(){

    }

    public ReturnMessage(String message, Object returnObject, HttpStatus code) {
        this.message = message;
        ReturnObject = returnObject;
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnObject() {
        return ReturnObject;
    }

    public void setReturnObject(Object returnObject) {
        ReturnObject = returnObject;
    }

    public HttpStatus getCode() {
        return Code;
    }

    public void setCode(HttpStatus code) {
        Code = code;
    }
}

