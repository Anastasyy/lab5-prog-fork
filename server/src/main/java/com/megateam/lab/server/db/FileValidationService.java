package com.megateam.lab.server.db;

import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.impl.DatabaseFileNotExistsException;
import com.megateam.lab.common.exceptions.impl.DatabaseFilePermissionException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.NonNull;

public class FileValidationService {
  public void validateSavingFileExistence(@NonNull File file) throws DatabaseException {
    if (!file.exists())
      throw new DatabaseFileNotExistsException("Specified database file does not exists.");
  }

  public void validateSavingFilePermissions(@NonNull File file) throws DatabaseException {
    if (!Files.isReadable(Path.of(file.toURI())))
      throw new DatabaseFilePermissionException(
          "Database file is not readable. Change the permissions.");

    if (!Files.isWritable(Path.of(file.toURI())))
      throw new DatabaseFilePermissionException(
          "Database file is not writable. Change the permissions.");
  }
}
