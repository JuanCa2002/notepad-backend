package com.ensolvers.mynotepad.my_notepad.config.converters;

import jakarta.persistence.AttributeConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeStringConverter implements AttributeConverter<LocalTime, String> {
    private static final DateTimeFormatter TO_STRING_PATTERN = DateTimeFormatter.ofPattern("HH:mm:ss");

    public String convertToDatabaseColumn(final LocalTime attribute) {
        return attribute == null ? null : attribute.format(TO_STRING_PATTERN);
    }

    public LocalTime convertToEntityAttribute(final String dbData) {
        return dbData == null ? null : LocalTime.parse(dbData, TO_STRING_PATTERN);
    }
}
