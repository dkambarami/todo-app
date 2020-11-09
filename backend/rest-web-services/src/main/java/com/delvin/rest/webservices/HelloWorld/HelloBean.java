package com.delvin.rest.webservices.HelloWorld;

public class HelloBean {

    String message = "";

    public HelloBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
