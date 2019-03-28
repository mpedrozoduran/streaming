package org.javeriana.sd.streaming.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Channel {
    private String address;
    private int rtpPort;
    private int udpPort;

    @Override
    public String toString() {
        return "Channel{address='" + address + ",rtpPort=" + rtpPort + ",udpPort=" + udpPort + "}";
    }
}
