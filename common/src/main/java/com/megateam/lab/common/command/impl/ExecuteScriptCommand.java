package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.*;
import com.megateam.lab.common.exceptions.impl.DataclassParsingException;
import com.megateam.lab.common.exceptions.impl.InvalidAmountOfArgumentsException;
import com.megateam.lab.common.exceptions.impl.InvalidArgumentException;
import com.megateam.lab.common.exceptions.impl.ScriptIsAlreadyExecutedException;
import com.megateam.lab.common.executors.ScriptExecutor;
import com.megateam.lab.common.util.Printer;
import java.io.File;
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
  public boolean execute()
      throws EnvException, DatabaseException, CommandArgumentsException, ExecutionException {
    if (args.size() != 1) {
      throw new InvalidAmountOfArgumentsException(
          "This command accepts only 1 argument. Provided: " + args.size());
    }

    File file = fileManipulationService.retrieveFileByName(args.get(0));
    if (ScriptExecutor.EXECUTED_SCRIPTS.contains(file.getName())) {
      throw new ScriptIsAlreadyExecutedException(
          "Script " + file.getName() + " is already executed");
    }
    try {
      List<Exchange> resolvedCommands = scriptResolver.resolve(file);

      ScriptExecutor.EXECUTED_SCRIPTS.add(file.getName());
      boolean executionStatus = scriptExecutor.execute(resolvedCommands);
      ScriptExecutor.EXECUTED_SCRIPTS.remove(file.getName());
      return executionStatus;
    } catch (ResolverException | DataclassParsingException e) {
      throw new InvalidArgumentException(e.getMessage());
    } finally {
      ScriptExecutor.EXECUTED_SCRIPTS.remove(file.getName());
    }
  }
}
