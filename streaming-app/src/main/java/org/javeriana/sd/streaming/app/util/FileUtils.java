package org.javeriana.sd.streaming.app.util;

import org.javeriana.sd.streaming.app.model.Channel;
import org.javeriana.sd.streaming.app.model.Channels;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    public static void save(String data, String filepath) throws IOException {
        Path path = Paths.get(filepath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.write(path, data.getBytes(), StandardOpenOption.CREATE_NEW);
    }

    public static Object read(String filepath, Class aClass) throws IOException {
        Path path = Paths.get(filepath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        String json = new String(Files.readAllBytes(path));
        Channels channels = (Channels) GsonUtils.toObject(json, aClass);
        return (channels == null) ? new Channels() : channels;
    }
}
