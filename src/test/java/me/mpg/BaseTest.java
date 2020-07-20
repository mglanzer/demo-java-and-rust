package me.mpg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseTest {

    protected static final JsonHelper JSON_HELPER = new JsonHelper(new ObjectMapper());

    protected List<Data> createData(int num) {
        return IntStream.range(0, num)
                .mapToObj(i -> Data.builder().number(i).build())
                .collect(Collectors.toList());
    }
}
