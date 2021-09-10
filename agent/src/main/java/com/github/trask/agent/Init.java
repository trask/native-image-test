package com.github.trask.agent;

import java.util.concurrent.atomic.AtomicBoolean;

public class Init {

  private static final AtomicBoolean initialized = new AtomicBoolean();

  public static void init() {
    if (!initialized.getAndSet(true)) {
      System.out.println("hi from init!");
    }
  }
}
