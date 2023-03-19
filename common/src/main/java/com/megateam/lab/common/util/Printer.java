package com.megateam.lab.common.util;

public interface Printer {
  void print(String printable);

  void println(String printable);

  void printf(String printable, Object... args);
}
