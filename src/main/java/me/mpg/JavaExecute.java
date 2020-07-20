package me.mpg;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;

/***
 * Java implementation similar to that of the Rust version executed by Native
 * @see Native
 */
public class JavaExecute {

    private static final JsonHelper JSON_HELPER = new JsonHelper(new ObjectMapper());

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
