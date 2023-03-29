package com.megateam.lab.common.util;

import com.sun.istack.NotNull;
import java.io.File;

public class FileManipulationService {
  public File retrieveFileByName(@NotNull String filename) {
    return new File(filename);
  }
}
