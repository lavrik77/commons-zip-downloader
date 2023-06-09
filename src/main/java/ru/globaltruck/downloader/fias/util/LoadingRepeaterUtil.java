package ru.globaltruck.downloader.fias.util;

public class LoadingRepeaterUtil {

    private static String fileName = null;
    private static boolean flag = false;

    public static void setFileName(String name) {
        fileName = name;
    }
    
    public static String getFileName() {
        return fileName;
    }

    public static boolean equals(String name) {
        if (flag) {
            return name != null && name.equals(fileName);
        } else {
            return true;
        }
    }

    public static void clearName() {
        fileName = null;
    }

    public static void onErrFlag() {
        LoadingRepeaterUtil.flag = true;
    }

    public static void offErrFlag() {
        LoadingRepeaterUtil.flag = false;
    }
}
