package com.delvin.basicAuth;

public class AuthenticationBean {

    String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthenticationBean [message=" + message + "]";
    }

    
    

}
