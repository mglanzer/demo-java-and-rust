package me.mpg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import lombok.Builder;

/***
 * Java implementation similar to that of the Rust version executed by Native
 * @see Native
 */
public class JavaAfterburnerExecute {

    private static final JsonHelper JSON_HELPER;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AfterburnerModule());
        JSON_HELPER = new JsonHelper(objectMapper);
    }

    @lombok.Data
    @Builder
    public static class Response {
        private int total;
    }

    public static String execute(String json) {
        Message message = JSON_HELPER.readJson(json, Message.class);

        int total = message.getData().stream()
                .map(Data::getNumber)
                .reduce(0, Integer::sum);

        return JSON_HELPER.toJson(Response.builder().total(total).build());
    }
}
