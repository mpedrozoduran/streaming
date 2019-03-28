package org.javeriana.sd.streaming.app.util;

import org.javeriana.sd.streaming.app.field.Constants;

import java.io.IOException;

public class SystemUtils {
    private static String VLC_MACOSX_PATH = "/Applications/VLC.app/Contents/MacOS/";
    private static String[] VLC_MACOSX_RTP =
            new String[] {
                    VLC_MACOSX_PATH + "VLC", "-vvv \"/Users/mpedrozoduran/Downloads/The Ninth Gate (1999)/The.Ninth.Gate.1999.720p.BluRay.x264.YIFY.mp4\"", "--sout '#rtp{dst=%S,port=%S,sdp=rtsp://%S:8080/test.sdp}'"};
    private static  String[] VLC_MACOSX_RTP_CLIENT = {VLC_MACOSX_PATH + "VLC", "rtsp://%S:8080/test.sdp"};

    private static String[] VLC_LINUX_RTP = {"vlc", "-vvv \"/Users/mpedrozoduran/Downloads/The Ninth Gate (1999)/The.Ninth.Gate.1999.720p.BluRay.x264.YIFY.mp4\"", "--sout '#rtp{dst=%S,port=%S,sdp=rtsp://%S:8080/test.sdp}'"};
    private static String[] VLC_LINUX_RTP_CLIENT = {"vlc", "rtsp://%S:8080/test.sdp"};

    public static int checkOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("osx")) {
            return Constants.OS_OSX;
        } else if (os.contains("nix")) {
            return Constants.OS_UNIX;
        } else {
            return Constants.OS_WIN;
        }
    }

    public static void runProgram(String mode, String address, int port) throws IOException {
        if (checkOS() == Constants.OS_OSX) {
            if (mode.equals("server"))  {
                String[] rtpCommand = SystemUtils.VLC_MACOSX_RTP;
                rtpCommand[2] = String.format(rtpCommand[2], address, port);
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(rtpCommand);
            } else {
                String[] rtpCommand = SystemUtils.VLC_MACOSX_RTP_CLIENT;
                rtpCommand[1] = String.format(rtpCommand[1], address);
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(rtpCommand);
            }
        } else if (checkOS() == Constants.OS_UNIX) {
            if (mode.equals("server"))  {
                String[] rtpCommand = SystemUtils.VLC_LINUX_RTP;
                rtpCommand[2] = String.format(rtpCommand[2], address, port);
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(rtpCommand);
            } else {
                String[] rtpCommand = SystemUtils.VLC_LINUX_RTP_CLIENT;
                rtpCommand[1] = String.format(rtpCommand[1], address);
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(rtpCommand);
            }
        }
    }
}
