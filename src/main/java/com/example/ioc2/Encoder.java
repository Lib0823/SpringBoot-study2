package com.example.ioc2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder {

    private IEncoder iEncoder; // 외부에서 주입받음 (DI)

    public Encoder(IEncoder iEncoder) {    // 어떤 클래스(bean)을 사용할지
        this.iEncoder = iEncoder;

        //this.iEncoder = new Base64Encoder();
        //this.iEncoder = new UrlEncoder();
    }

    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }

}
