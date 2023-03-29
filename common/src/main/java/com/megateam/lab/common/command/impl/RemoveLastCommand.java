package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

public class RemoveLastCommand extends Command {
  @Builder(setterPrefix = "set")
  public RemoveLastCommand(
      @NonNull Printer printer, @NonNull CommandSource source, @NonNull List<String> args) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    dao.removeLast();
    return true;
  }
}
