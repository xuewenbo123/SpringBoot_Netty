package com.UIChart;

public class A {


    public static void main(String[] args) {
        get();
    }


    public static void get(){
        UI ui = new UI();
        ui.cli = new Client(ui);
        ui.initFrame();
        ui.showLogin();
    }

}
