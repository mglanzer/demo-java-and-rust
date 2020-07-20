package me.mpg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaExecuteTest extends BaseTest {

    @Test
    public void execute() {
        Message message = Message.builder()
                .cmd("Stats")
                .data(createData(4))
                .build();

        String actual = JavaExecute.execute(JSON_HELPER.toJson(message));
        assertEquals("{\"total\":6}", actual);
    }
}
