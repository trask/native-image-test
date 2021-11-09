package com.github.trask.app;

import static java.util.concurrent.TimeUnit.HOURS;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("hi from main!");
    Thread.sleep(HOURS.toMillis(1));
  }
}
