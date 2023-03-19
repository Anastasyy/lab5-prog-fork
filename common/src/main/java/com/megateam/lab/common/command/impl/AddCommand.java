package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.CommandUsesElements;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.util.Printer;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

public class AddCommand extends Command {
  private CommandSource source;
  private List<String> args;
  private Dao<Ticket> dao;

  public AddCommand(
      @NonNull Printer printer, @NonNull CommandSource source, @NonNull Dao<Ticket> dao) {
    super(printer, source, CommandUsesElements.USES, Collections.emptyList());
    this.dao = dao;
  }

  @Override
  public boolean execute() {
    Ticket ticket = (Ticket) additionalArgs.get(0);
    dao.add(ticket);
    return true;
  }
}
