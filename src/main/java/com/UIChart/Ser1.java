package com.UIChart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

class Ser1 extends Thread{

    public int j;

    public void run(){
        try{
            DataInputStream read = new DataInputStream((Server.soc[Server.j]).getInputStream());
            j=Server.j;
            while(true){
                String con = read.readUTF();

                if(con!=null){


                    System.out.println("该线程j为"+j);
                    for(int i = 0;i<Server.soc.length;i++){
                        if((i!=j)&&(Server.soc[i]!=null)){
                            DataOutputStream dos = new DataOutputStream((Server.soc[i]).getOutputStream());
                            dos.writeUTF(Server.name[Server.j]+"发送于 "+(new Date()));
                            dos.writeUTF(con);
                        }
                    }


                }else{break;}
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
