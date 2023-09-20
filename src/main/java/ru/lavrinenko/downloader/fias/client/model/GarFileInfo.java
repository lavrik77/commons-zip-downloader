package ru.lavrinenko.downloader.fias.client.model;

import lombok.Data;

@Data
public class GarFileInfo {
    private String versionId;
    private String textVersion;
    private String fiasCompleteDbfUrl;
    private String fiasCompleteXmlUrl;
    private String fiasDeltaDbfUrl;
    private String fiasDeltaXmlUrl;
    private String kladr4ArjUrl;
    private String kladr47ZUrl;
    private String garXMLFullURL;
    private String garXMLDeltaURL;
    private String date;
}
