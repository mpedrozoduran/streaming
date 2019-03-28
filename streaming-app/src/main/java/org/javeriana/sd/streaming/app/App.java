package org.javeriana.sd.streaming.app;

import com.beust.jcommander.JCommander;
import lombok.extern.log4j.Log4j2;
import org.javeriana.sd.streaming.app.field.Constants;
import org.javeriana.sd.streaming.app.model.Args;
import org.javeriana.sd.streaming.app.model.Channel;
import org.javeriana.sd.streaming.app.model.Channels;
import org.javeriana.sd.streaming.app.net.UDPClientSocketManager;
import org.javeriana.sd.streaming.app.net.UDPServerSocketManager;
import org.javeriana.sd.streaming.app.net.message.Message;
import org.javeriana.sd.streaming.app.util.FileUtils;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@Log4j2
public class App 
{
    private static final long DEFAULT_WAIT_TIME = 10000;

    private UDPServerSocketManager serverSocketManager;

    public static void main( String[] args )
    {
        App app = new App();
        try {
            app.start(args);
        } catch (IOException | InterruptedException e) {
            log.error(e);
        }
    }

    private void start(String[] args) throws IOException, InterruptedException {
        Args params = new Args();
        JCommander.newBuilder().addObject(params).build().parse(args);
        if (params.getMode().equals("server")) {
            startServer(params);
        } else if (params.getMode().equals("client")) {
            startClient(params);
        } else {
            log.error("No <mode> was specified, must be one of the following: server or client");
        }
    }

    private void startClient(Args args) throws InterruptedException, IOException {
        serverSocketManager = new UDPServerSocketManager(args.getPort(), args.getChannelFile());
        serverSocketManager.startThread();
        Thread.sleep(2000);
        serverSocketManager.send(new Message(Constants.UDP_MESSAGE_SEARCH_STREAMING_DEVICES, args.getPort()));
        log.info(String.format("Waiting for %d seconds to receive streaming devices...", App.DEFAULT_WAIT_TIME / 1000));
        Thread.sleep(App.DEFAULT_WAIT_TIME);
        Channels channels = (Channels) FileUtils.read(args.getChannelFile(), Channels.class);
        if (channels != null && channels.getChannels() != null && !channels.getChannels().isEmpty()) {
            log.info(String.format("Found %d streaming devices...", channels.getChannels().size()));
            Channel channel = channels.getChannels().get(0);
            new UDPClientSocketManager(
                    channel.getAddress(), channel.getUdpPort(),
                    new Message(Constants.UDP_MESSAGE_START_STREAMING_REQUEST, args.getPort()),
                    false)
                    .startThread();
        } else {
            log.error("No channel found to stream!");
        }
    }

    private void startServer(Args args) {
        serverSocketManager = new UDPServerSocketManager(args.getPort(), args.getChannelFile());
        serverSocketManager.startThread();
    }
}
