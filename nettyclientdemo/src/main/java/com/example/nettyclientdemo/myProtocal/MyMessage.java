package com.example.nettyclientdemo.myProtocal;

import java.io.Serializable;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:15
 * Modified By:
 * Description: 消息的主题*/


public class MyMessage implements Serializable {
    private MyHeader luckHeader;
    private String content;

    public MyMessage(MyHeader luckHeader, String content) {
        this.luckHeader = luckHeader;
        this.content = content;
    }

    public MyMessage(MyHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public MyHeader getLuckHeader() {
        return luckHeader;
    }

    public void setLuckHeader(MyHeader luckHeader) {
        this.luckHeader = luckHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,contentLength=%d,sessionId=%s,content=%s]",
                luckHeader.getVersion(),
                luckHeader.getContentLength(),
                luckHeader.getSessionId(),
                content);
    }
}
