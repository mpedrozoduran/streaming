package org.javeriana.sd.streaming.app.util;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class SystemUtilsTest {

    private String resourceToStream =
            "/Users/mpedrozoduran/Downloads/The_Ninth_Gate_1999/The.Ninth.Gate.1999.720p.BluRay.x264.YIFY.mp4";

    @Test
    public void runProgramClient() throws IOException {
        SystemUtils.runProgram("client", "192.168.1.13", 8889, "", resourceToStream);
    }

    @Test
    public void runProgramServer() throws IOException {
        SystemUtils.runProgram("server", "192.168.43.80", 8889, "", resourceToStream);
    }
}