package com.TCP_netty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tcp")
public class TCPController {

    @RequestMapping("/get")
    @ResponseBody
    public String getResult() throws Exception{

        new TCPClient().connect(9169,"115.28.75.240");


        return "";
    }


}
