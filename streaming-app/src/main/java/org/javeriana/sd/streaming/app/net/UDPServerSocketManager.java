package org.javeriana.sd.streaming.app.net;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.javeriana.sd.streaming.app.field.Constants;
import org.javeriana.sd.streaming.app.model.Channel;
import org.javeriana.sd.streaming.app.net.message.Message;
import org.javeriana.sd.streaming.app.net.message.StreamingDevicesMessage;
import org.javeriana.sd.streaming.app.net.message.processor.FoundStreamingDevicesProcessor;
import org.javeriana.sd.streaming.app.net.message.processor.RemoveStreamingDevicesProcessor;
import org.javeriana.sd.streaming.app.util.GsonUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

@Log4j2
public class UDPServerSocketManager extends Thread {

    private int PORT = 9997;
    private int DEFAULT_RTSP_PORT = 9998;
    private boolean isThreadAlive = false;
    private DatagramSocket socket;

    private final String channelPublicFilePath;

    public UDPServerSocketManager(int port, String channelPublicFilePath) {
        this.PORT = port;
        this.channelPublicFilePath = channelPublicFilePath;
    }

    public void startThread() {
        stopThread();
        this.isThreadAlive = true;
        startThread();
    }

    public void stopThread() {
        this.isThreadAlive = false;
        if (socket != null) {
            socket.disconnect();
            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(PORT);
            socket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (isThreadAlive) {
            byte[] buffer = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                processIncomingMessage(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Message message) throws IOException {
        if (socket != null) {
            Gson gson = new Gson();
            String payload = gson.toJson(message);
            socket.send(new DatagramPacket(payload.getBytes(), payload.getBytes().length));
        }
    }

    private void processIncomingMessage(DatagramPacket packet) throws IOException {
        String data = extractPacketData(packet);
        if (data == null) {
            log.warn("Packet payload is null!");
            return;
        }
        Message message = (Message) GsonUtils.toObject(data, Message.class);
        if (message != null) {
            switch (message.getCode()) {
                case Constants.UDP_MESSAGE_SEARCH_STREAMING_DEVICES:
                    send(new StreamingDevicesMessage(Constants.UDP_MESSAGE_FOUND_STREAMING_DEVICES, "",
                            new Channel(InetAddress.getLocalHost().getHostAddress(), DEFAULT_RTSP_PORT)));
                case Constants.UDP_MESSAGE_FOUND_STREAMING_DEVICES:
                    new FoundStreamingDevicesProcessor(channelPublicFilePath, data).execute();
                    break;
                case Constants.UDP_MESSAGE_START_STREAMING:
                    //TODO call vlc and start in rtp mode
                    break;
                case Constants.UDP_MESSAGE_STOP_STREAMING:
                    new RemoveStreamingDevicesProcessor(channelPublicFilePath, data).execute();
                    break;
            }
        }
    }

    private String extractPacketData(DatagramPacket packet) {
        if (validateIncomingPacket(packet)) {
            String payload = new String(packet.getData());
            int lastCharIndex = payload.lastIndexOf('}');
            if (!payload.isEmpty() && lastCharIndex != -1) {
                return payload.substring(0, lastCharIndex + 1);
            }
        }
        return null;
    }

    private boolean validateIncomingPacket(DatagramPacket packet) {
        if (packet == null) {
            log.warn("Incoming packet is null!");
            return false;
        }
        return true;
    }
}
