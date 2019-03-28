package org.javeriana.sd.streaming.app.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Channels {
    private List<Channel> channels;

    public void addChannel(Channel channel) {
        if (channels == null) {
            channels = new ArrayList<>();
        }
        if (!channels.contains(channel)) {
            channels.add(channel);
        }
    }

    public void removeChannel(Channel channel) {
        if (channels != null) {
            channels.remove(channel);
        }
    }
}
