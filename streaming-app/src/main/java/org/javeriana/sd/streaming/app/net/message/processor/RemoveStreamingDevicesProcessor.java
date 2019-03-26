package org.javeriana.sd.streaming.app.net.message.processor;

import lombok.AllArgsConstructor;
import org.javeriana.sd.streaming.app.model.Channels;
import org.javeriana.sd.streaming.app.net.message.StreamingDevicesMessage;
import org.javeriana.sd.streaming.app.util.FileUtils;
import org.javeriana.sd.streaming.app.util.GsonUtils;

import java.io.IOException;

@AllArgsConstructor
public class RemoveStreamingDevicesProcessor implements Processor {

    private String channelPublicFilePath;
    private String data;

    @Override
    public void execute() throws IOException {
        StreamingDevicesMessage streamingDevicesMessage =
                (StreamingDevicesMessage) GsonUtils.toObject(data, StreamingDevicesMessage.class);
        Channels channels = (Channels) FileUtils.read(channelPublicFilePath, Channels.class);
        channels.removeChannel(streamingDevicesMessage.getChannel());
        FileUtils.save(GsonUtils.toJson(channels), channelPublicFilePath);
    }
}
