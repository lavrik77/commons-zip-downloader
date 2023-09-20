package ru.lavrinenko.downloader.fias.component;

import net.bytebuddy.implementation.bytecode.Throw;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public interface ColumnTypes {

    Map<String, Class<?>> types = new HashMap<>();

    default Class<?> type(String column) {
        return types.get(column);
    }

    @SuppressWarnings("unchecked")
    default <T> T getCastValue(String name, String value) {
        Class<?> clazz = type(name);
        T result = null;
        try {
            if ("String".equalsIgnoreCase(clazz.getSimpleName())) {
                result = (T) value;
            } else if ("UUID".equalsIgnoreCase(clazz.getSimpleName())) {
                result = (T) clazz.getDeclaredMethod("fromString", String.class).invoke(clazz, value);
            } else if ("OffsetDateTime".equalsIgnoreCase(clazz.getSimpleName())){
                result = (T) OffsetDateTime.ofInstant(Instant.parse( value + "T00:00:00Z"), ZoneId.of("UTC"));
            } else if ("Boolean".equalsIgnoreCase(clazz.getSimpleName())){
                if ("1".equals(value)) {
                    result = (T) Boolean.TRUE;
                } else if ("0".equals(value)) {
                    result = (T) Boolean.FALSE;
                } else {
                    result = (T) clazz.getDeclaredMethod("valueOf", String.class).invoke(clazz, value);
                }
            } else {
                result = (T) clazz.getDeclaredMethod("valueOf", String.class).invoke(clazz, value);
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            handleException(e);
        }
        return result;
    }

    default void handleException(Throwable t) {}
}
