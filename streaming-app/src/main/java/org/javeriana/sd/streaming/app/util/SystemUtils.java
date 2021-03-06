package org.javeriana.sd.streaming.app.util;

import lombok.extern.log4j.Log4j2;
import org.javeriana.sd.streaming.app.field.Constants;

import java.io.IOException;

@Log4j2
public class SystemUtils {
    private static String VLC_MACOSX_RTP = "%s/vlc_macosx_rtp.sh %s %s %s";
    private static String VLC_MACOSX_RTP_CLIENT = "%s/vlc_macosx_client.sh %s %s";

    private static String VLC_LINUX_RTP = "%s/vlc_linux_rtp.sh %s %s %s";
    private static String VLC_LINUX_RTP_CLIENT = "%s/vlc_linux_client.sh %s %s";

    public static int checkOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("os x")) {
            return Constants.OS_OSX;
        } else if (os.contains("nix") || os.contains("nux")) {
            return Constants.OS_UNIX;
        } else {
            return Constants.OS_WIN;
        }
    }

    public static void runProgram(String mode, String address, int port, String basePath, String resourceToStream) throws IOException {
        if (checkOS() == Constants.OS_OSX) {
            if (mode.equals("server"))  {
                Runtime runtime = Runtime.getRuntime();
                String url = String.format(SystemUtils.VLC_MACOSX_RTP, basePath, address, port, resourceToStream);
                runtime.exec(url);
            } else {
                Runtime runtime = Runtime.getRuntime();
                String url = String.format(SystemUtils.VLC_MACOSX_RTP_CLIENT, basePath, address, port);
                runtime.exec(url);
            }
        } else if (checkOS() == Constants.OS_UNIX) {
            if (mode.equals("server"))  {
                Runtime runtime = Runtime.getRuntime();
                String url = String.format(SystemUtils.VLC_LINUX_RTP, basePath, address, port, resourceToStream);
                runtime.exec(url);
            } else {
                Runtime runtime = Runtime.getRuntime();
                String url = String.format(SystemUtils.VLC_LINUX_RTP_CLIENT, basePath, address, port);
                runtime.exec(url);
            }
        }
    }
}
