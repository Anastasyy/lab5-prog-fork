package com.megateam.lab.common.util;

public class ConsolePrinter implements Printer {
  @Override
  public void print(String printable) {
    System.out.print(printable);
  }

  @Override
  public void println(String printable) {
    System.out.println(printable);
  }

  @Override
  public void printf(String printable, Object... args) {
    System.out.printf(printable, args);
  }
}
