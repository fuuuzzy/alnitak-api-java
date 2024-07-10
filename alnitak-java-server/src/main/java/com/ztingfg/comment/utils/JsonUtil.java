package com.ztingfg.comment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.base.Strings;
import com.ztingfg.comment.exception.JacksonParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class JsonUtil {

    public static final String DEFAULT_DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT_STRING);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_TIME;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    static {
        final SimpleDateFormat defaultSimpleDateFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT_STRING);
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));

        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, defaultSimpleDateFormatter));
        javaTimeModule.addDeserializer(Date.class,
                new DateDeserializers.DateDeserializer(new DateDeserializers.DateDeserializer(),
                        defaultSimpleDateFormatter, DEFAULT_DATE_FORMAT_STRING));

        OBJECT_MAPPER.registerModule(javaTimeModule);

        SimpleModule simpleModule = new SimpleModule();
        OBJECT_MAPPER.registerModule(simpleModule);

        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T toObject(byte[] json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JacksonParseException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new JacksonParseException(e);
        }
    }

    public static <T> String toJson(T obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JacksonParseException(e);
        }
    }

    public static <T> T toObject(String json, TypeReference<T> tTypeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, tTypeReference);
        } catch (JsonProcessingException e) {
            throw new JacksonParseException(e);
        }
    }

    public static <T> T toObject(byte[] json, TypeReference<T> tTypeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, tTypeReference);
        } catch (IOException e) {
            throw new JacksonParseException(e);
        }
    }

    public static <T> T pathToObject(String json, String path, TypeReference<T> tTypeReference) {
        try {
            String pathJson = OBJECT_MAPPER.readTree(json).at(path).toString();
            if (Strings.isNullOrEmpty(pathJson)) {
                return null;
            }
            return OBJECT_MAPPER.readValue(pathJson, tTypeReference);
        } catch (IOException e) {
            throw new JacksonParseException(e);
        }
    }

    private JsonUtil() {
    }
}
