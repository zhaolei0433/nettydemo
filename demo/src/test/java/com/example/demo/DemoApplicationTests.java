package com.example.demo;

import com.example.demo.client.Client;
import com.example.demo.client.codec.DefaultFactoryListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

        byte[] bodyData = new byte[3];
        bodyData[0] = (byte) 0;
        byte[] didByte = Toolkit.shortToBigEndianByte((short) 254);
        System.arraycopy(didByte, 0, bodyData, 1, 2);
        byte CMD_ID_IFS_INDEX = 0x07;
        byte SUB_CMD_ID_DISK_STATUS_MANUAL_SET_RES = 0x01;
        String Ip = "192.168.48.153:9200";
        DefaultFactoryListener listener = new DefaultFactoryListener();
        try {
            System.out.println(Client.sendData(listener,CMD_ID_IFS_INDEX,SUB_CMD_ID_DISK_STATUS_MANUAL_SET_RES,bodyData,Ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

