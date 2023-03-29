package com.megateam.lab.common.util;

import java.util.List;

public interface Printer {
  void print(String printable);

  void println(String printable);

  void printf(String printable, Object... args);

  void printList(List<?> list);
}
