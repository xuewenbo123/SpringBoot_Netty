package com.finalTCP;

public class ClientStart {

    public static void main(String[] args) throws Exception {
            int port=9169 ;
            new TCPClient().connect(port, "不告诉你");
    }
}
