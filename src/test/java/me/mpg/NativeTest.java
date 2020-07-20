package me.mpg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NativeTest extends BaseTest {

    @Test
    public void invalid_json() {
        String actual = Native.execute("Invalid JSON");
        assertEquals("expected value at line 1 column 1", actual);
    }

    @Test
    public void stats() {
        Message message = Message.builder()
                .cmd("Stats")
                .data(createData(4))
                .build();

        String actual = Native.execute(JSON_HELPER.toJson(message));
        assertEquals("{\"total\":6}", actual);
    }
}
