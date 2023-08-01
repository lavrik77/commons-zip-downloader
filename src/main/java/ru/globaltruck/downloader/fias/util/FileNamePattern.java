package ru.globaltruck.downloader.fias.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class FileNamePattern {

    public static final Pattern ADDR_OBJ_PATTERN = Pattern
            .compile("^([0-9]{2}/)?AS_ADDR_OBJ_[0-9]{8}_" +
                    "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}.XML$");
    public static final Pattern HOUSE_PATTERN = Pattern
            .compile("^([0-9]{2}/)?AS_HOUSES_[0-9]{8}_" +
                    "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}.XML$");

    public static final Pattern ADM_HIERARCHY_PATTERN = Pattern
            .compile("^([0-9]{2}/)?AS_ADM_HIERARCHY_[0-9]{8}_" +
                    "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}.XML$");

    public static final Pattern MUN_HIERARCHY_PATTERN = Pattern
            .compile("^([0-9]{2}/)?AS_MUN_HIERARCHY_[0-9]{8}_" +
                    "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}.XML$");
}
