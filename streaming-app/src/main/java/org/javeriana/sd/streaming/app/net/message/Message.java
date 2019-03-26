package org.javeriana.sd.streaming.app.net.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private int code;
    private String message;
}
