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

public class InfoCommand extends Command {
  @Builder(setterPrefix = "set")
  public InfoCommand(
      @NonNull Printer printer,
      @NonNull CommandSource source,
      @NonNull List<String> args
  ) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    printer.println("Collection type: ArrayList");
    printer.println("Creation date: " + dao.getCreationDate());
    printer.println("Elements count: " + dao.size());
    return true;
  }
}
