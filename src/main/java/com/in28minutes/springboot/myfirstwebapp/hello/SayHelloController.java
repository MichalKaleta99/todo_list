package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @ResponseBody
@RequestMapping("say-hello")
    public String sayHello(){
        return "Hello! What are you learning today?";
    }
    @ResponseBody
    @RequestMapping("say-hello-html")
    public String sayHelloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<hmtl>\n" +
                "    <head>\n" +
                "        <title> My first HTML Page</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "    My first html page with body\n" +
                "    </body>\n" +
                "</hmtl>");

        return sb.toString();
    }
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "say-hello";
    }


}
