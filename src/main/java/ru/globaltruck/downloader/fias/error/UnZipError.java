package ru.globaltruck.downloader.fias.error;

import java.io.IOException;

public class UnZipError extends Throwable {
    public UnZipError(String message, Exception e) {
        super(message, e);
    }
}
