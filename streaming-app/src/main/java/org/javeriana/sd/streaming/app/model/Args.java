package org.javeriana.sd.streaming.app.model;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class Args {

    @Parameter(names = { "-mode" }, description = "Starting mode: server or client", required = true)
    private String mode;

    @Parameter(names = { "-port" }, description = "For <server> mode, sets the listening port", required = true)
    private Integer port;

    @Parameter(names = { "-channelFile" }, description = "Path where to save channels", required = true)
    private String channelFile;

    @Parameter(names = { "-basePath" }, description = "Base directory structure path", required = true)
    private String basePath;

    @Parameter(names = { "-resourceToStream" }, description = "Path to the resource to stream", required = true)
    private String resourceToStream;
}
