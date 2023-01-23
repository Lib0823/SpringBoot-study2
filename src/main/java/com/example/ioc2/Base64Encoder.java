package com.example.ioc2;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder")  // Bean으로 등록시킴
public class Base64Encoder implements IEncoder {

    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

}
