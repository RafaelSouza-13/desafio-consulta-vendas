package com.devsuperior.dsmeta.dto.error;

import java.time.Instant;

public class CustomError {
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String path;

    public CustomError(Instant timeStamp, int status, String error, String path) {
        this.timeStamp = timeStamp;
        this.path = path;
        this.error = error;
        this.status = status;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
