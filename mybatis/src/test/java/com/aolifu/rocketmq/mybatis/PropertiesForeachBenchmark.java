package com.aolifu.rocketmq.mybatis;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class PropertiesForeachBenchmark {

    @Param({"50", "100"})
    public int arg;

    @Benchmark
    public void keySet(Blackhole bh) {
        final Properties properties = new Properties();
        for (int i = 0; i < arg; i++) {
            properties.put("key" + i, "value");
        }
        for (Object key : properties.keySet()) {
            key.toString();
            properties.get(key).toString();
        }
        bh.consume(properties);
    }

    @Benchmark
    public void entrySet(Blackhole bh) {
        final Properties properties = new Properties();
        for (int i = 0; i < arg; i++) {
            properties.put("key" + i, "value");
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        bh.consume(properties);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + PropertiesForeachBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
