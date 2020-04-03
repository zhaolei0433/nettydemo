package com.example.nettyclientdemo;

import com.example.nettyclientdemo.myProtocal.MyHeader;
import com.example.nettyclientdemo.myProtocal.MyMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

import static com.example.nettyclientdemo.Client.sendMessage;


@SpringBootApplication
public class NettyclientdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyclientdemoApplication.class, args);
        int version = 1;
        String sessionId = UUID.randomUUID().toString();
        String content = "hi,zhaolei";

        MyHeader header = new MyHeader(version, content.length(), sessionId);
        MyMessage message = new MyMessage(header, content);

        System.out.println(message);
        sendMessage(message);
    }

}

