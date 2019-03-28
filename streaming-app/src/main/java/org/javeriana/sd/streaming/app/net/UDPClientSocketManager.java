package org.javeriana.sd.streaming.app.net;

import lombok.extern.log4j.Log4j2;
import org.javeriana.sd.streaming.app.net.message.Message;
import org.javeriana.sd.streaming.app.util.GsonUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Log4j2
public class UDPClientSocketManager extends Thread {

    private final String address;
    private int DEFAULT_RTSP_PORT = 9998;
    private int PORT = 9997;
    private final Message message;
    private final boolean isBroadcast;
    private DatagramSocket socket;

    public UDPClientSocketManager(String address, int port, Message message, boolean isBroadcast) {
        this.address = address;
        this.PORT = port;
        this.message = message;
        this.isBroadcast = isBroadcast;
    }

    public void startThread() {
        start();
    }

    public void stopThread() {
        if (socket != null) {
            socket.disconnect();
            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket();
            if (isBroadcast) {
                socket.setBroadcast(true);
            }
            String payload = GsonUtils.toJson(message);
            socket.send(new DatagramPacket(payload.getBytes(), payload.getBytes().length, InetAddress.getByName(address), PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
