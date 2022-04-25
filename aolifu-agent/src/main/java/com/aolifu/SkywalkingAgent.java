package com.aolifu;

import java.lang.instrument.Instrumentation;

public class SkywalkingAgent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Hello,This is a Skywalking Handbook javaagent demo");
        instrumentation.addTransformer(new ClassFileTransformer());
    }
}
