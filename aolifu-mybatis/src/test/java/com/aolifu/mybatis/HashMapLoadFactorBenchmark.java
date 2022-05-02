package com.aolifu.mybatis;

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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class HashMapLoadFactorBenchmark {

    @Param({"20", "50"})
    public int arg;

    @Benchmark
    public void loadFactorDefault(Blackhole bh) {
        final Map<String, String> map = new HashMap<>(arg);
        for (int i = 0; i < arg; i++) {
            map.put("key" + i, "value");
        }
        bh.consume(map);
    }

    @Benchmark
    public void loadFactorOne(Blackhole bh) {
        final Map<String, String> map = new HashMap<>(arg, 1);
        for (int i = 0; i < arg; i++) {
            map.put("key" + i, "value");
        }
        bh.consume(map);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + HashMapLoadFactorBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }

}