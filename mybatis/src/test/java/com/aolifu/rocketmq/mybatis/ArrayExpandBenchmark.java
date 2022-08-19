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

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ArrayExpandBenchmark {

    @Param({"20", "50"})
    public int arg;

    @Benchmark
    public void loadFactorZero(Blackhole bh) {
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arg; i++) {
            set.add(i);
        }
        set.toArray(new Integer[0]);
        bh.consume(set);
    }

    @Benchmark
    public void loadFactorSize(Blackhole bh) {
        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arg; i++) {
            set.add(i);
        }
        set.toArray(new Integer[set.size()]);
        bh.consume(set);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + ArrayExpandBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
