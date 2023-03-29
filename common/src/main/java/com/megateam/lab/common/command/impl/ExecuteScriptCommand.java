package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

public class ExecuteScriptCommand extends Command {
  @Builder(setterPrefix = "set")
  public ExecuteScriptCommand(
      @NonNull Printer printer, @NonNull CommandSource source, @NonNull List<String> args) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException, CommandArgumentsException {
    //		fileManipulationService.retrieveFileByName();
    return true;
  }
}
