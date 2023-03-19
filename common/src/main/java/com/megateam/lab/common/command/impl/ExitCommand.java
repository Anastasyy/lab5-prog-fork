package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.CommandUsesElements;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.Collections;
import lombok.NonNull;

public class ExitCommand extends Command {
  public ExitCommand(@NonNull Printer printer, @NonNull CommandSource source) {
    super(printer, source, CommandUsesElements.NOT_USES, Collections.emptyList());
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    //		printer.print("Are you sure you want to exit? [y/Y - yes, other - no]");
    System.exit(0);
    return false;
  }
}
