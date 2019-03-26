package org.javeriana.sd.streaming.app.net.message;

import lombok.Data;
import org.javeriana.sd.streaming.app.model.Channel;

@Data
public class StreamingDevicesMessage extends Message {
    private Channel channel;

    public StreamingDevicesMessage(int code, String message) {
        super(code, message);
    }

    public StreamingDevicesMessage(int code, String message, Channel channel) {
        super(code, message);
        this.channel = channel;
    }
}
