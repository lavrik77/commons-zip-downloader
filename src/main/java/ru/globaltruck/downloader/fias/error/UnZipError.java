package ru.globaltruck.downloader.fias.error;

public class UnZipError extends Throwable {
    public UnZipError(String message, Exception e) {
        super(message, e);
    }
}
