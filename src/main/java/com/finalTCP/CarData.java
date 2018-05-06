package com.finalTCP;

import java.io.Serializable;

public class CarData implements Serializable{

    private final int a =  0xdf;
    private final int b = 0xfd;
    private String body;
    private final int c =  0x0d ;
    private final int d = 0x0a;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }
}
