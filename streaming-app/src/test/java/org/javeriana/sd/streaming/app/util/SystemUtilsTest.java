package org.javeriana.sd.streaming.app.util;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class SystemUtilsTest {

    @Test
    public void runProgramClient() throws IOException, InterruptedException {
        SystemUtils.runProgram("client", "192.168.1.13", 8889);
    }

    @Test
    public void runProgramServer() throws IOException, InterruptedException {
        SystemUtils.runProgram("server", "192.168.1.13", 8889);
    }
}