package me.mpg;

// JMH approach Adapted from: https://gist.github.com/msievers/ce80d343fc15c44bea6cbb741dde7e45

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JsonParseAndSumBenchmark extends AbstractBenchmark {

    private static final JsonHelper jsonHelper = new JsonHelper(new ObjectMapper());

    String testJson;

    @Setup(Level.Trial)
    public void setupBenchmark() {
        Message message = Message.builder()
                .cmd("Stats")
                .data(createData(4000))
                .build();

        testJson = jsonHelper.toJson(message);
    }

    @Benchmark
    public void native_execute() {
        Native.execute(testJson);
    }

    @Benchmark
    public void java_afterburner_execute() {
        JavaAfterburnerExecute.execute(testJson);
    }

    @Benchmark
    public void java_execute() {
        JavaExecute.execute(testJson);
    }

    @SuppressWarnings("SameParameterValue")
    private List<Data> createData(int num) {
        return IntStream.range(0, num)
                .mapToObj(i -> Data.builder().number(i).build())
                .collect(Collectors.toList());
    }
}
