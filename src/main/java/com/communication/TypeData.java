package com.communication;

public interface TypeData {

    //DeviceValue中的不同type来区分数据类型

    //模式
    byte PING = 1;

    byte PONG = 2;

    byte CUSTOME = 3;

    //*******************************
    byte PING_SEAT = 100;

    byte PONG_SEAT = 101;

    byte SERVER_RESPONSE = 102;

    byte SERVER_RESISTANT = 103;

}
