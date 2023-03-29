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

public class ExitCommand extends Command {
  @Builder(setterPrefix = "set")
  public ExitCommand(
          @NonNull Printer printer,
          @NonNull CommandSource source,
          @NonNull List<String> args
  ) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    //		printer.print("Are you sure you want to exit? [y/Y - yes, other - no]");

    printer.println("Performing application shutdown...");
    System.exit(0);
    return false;
  }
}
