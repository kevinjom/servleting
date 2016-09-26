package com.github.kevinjom.servleting.servlet2x;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperFactory {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperFactory() {
    }

    public static ObjectMapper get() {
        return objectMapper;
    }

}
