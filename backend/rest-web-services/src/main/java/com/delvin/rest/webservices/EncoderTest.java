package com.delvin.rest.webservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderTest {

    public static void main(String args[]) {
        BCryptPasswordEncoder bcpEncoder = new BCryptPasswordEncoder();

        for (int i = 0; i < 5; i++) {
            String encoded = bcpEncoder.encode("biden@2020");
            System.out.println(encoded);
        }
    }

}
