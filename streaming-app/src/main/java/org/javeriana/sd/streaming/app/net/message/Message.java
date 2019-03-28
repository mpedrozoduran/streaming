package org.javeriana.sd.streaming.app.net.message;

import lombok.Data;

@Data
public class Message {
    private int code;
    private String message;
    private int serverPort;

    public Message(int code) {
        this.code = code;
    }

    public Message(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(int code, int serverPort) {
        this.code = code;
        this.serverPort = serverPort;
    }
}
