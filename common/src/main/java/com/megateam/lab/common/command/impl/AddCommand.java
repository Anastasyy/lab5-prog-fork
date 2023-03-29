package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class AddCommand extends Command {
  @Builder(setterPrefix = "set")
  public AddCommand(
          @NonNull Printer printer,
          @NonNull CommandSource source,
          @NonNull List<String> args
  ) {
    super(printer, source, UsesElements.USES, args);
  }

  @Override
  public boolean execute() {
    Ticket ticket = (Ticket) additionalArgs.get(0);
    dao.add(ticket);
    return true;
  }
}
