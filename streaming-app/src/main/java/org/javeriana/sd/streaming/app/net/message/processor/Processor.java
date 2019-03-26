package org.javeriana.sd.streaming.app.net.message.processor;

import java.io.IOException;

public interface Processor {
    void execute() throws IOException;
}
