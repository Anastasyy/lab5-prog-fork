package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class SaveCommand extends Command {
  @Builder(setterPrefix = "set")
  public SaveCommand(
          @NonNull Printer printer,
          @NonNull CommandSource source,
          @NonNull List<String> args
  ) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    dao.save();
    printer.println("Data successfully saved.");
    return true;
  }
}
