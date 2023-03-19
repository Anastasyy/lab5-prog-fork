package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.CommandUsesElements;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.Collections;
import lombok.NonNull;

public class ClearCommand extends Command {
  @NonNull private Dao<Ticket> dao;

  public ClearCommand(@NonNull Printer printer, @NonNull CommandSource source, Dao<Ticket> dao) {
    super(printer, source, CommandUsesElements.NOT_USES, Collections.emptyList());
    this.dao = dao;
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    dao.clear();
    printer.println("Collection successfully cleared.");
    return true;
  }
}
