package com.github.trask.agent;

import java.lang.instrument.Instrumentation;

public class Premain {

  public static void premain(String agentArgs, Instrumentation instrumentation) {
    System.out.println("hi from premain!");
    instrumentation.addTransformer(new InitClassFileTransformer());
  }
}
