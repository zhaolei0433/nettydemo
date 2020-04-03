package com.example.nettydemo.myProtocal;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaolei
 * Create: 2019/2/1 14:15
 * Modified By:
 * Description: 协议消息头
 */
public class MyHeader  implements Serializable {
    // 协议版本
    private int version;
    // 消息内容长度
    private int contentLength;
    // 服务名称
    private String sessionId;

    public MyHeader(int version, int contentLength, String sessionId) {
        this.version = version;
        this.contentLength = contentLength;
        this.sessionId = sessionId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
