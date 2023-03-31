package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

public class PrintFieldDescendingPriceCommand extends Command {
  @Builder(setterPrefix = "set")
  public PrintFieldDescendingPriceCommand(
      @NonNull Printer printer, @NonNull CommandSource source, @NonNull List<String> args) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException {
    List<Ticket> tickets = dao.getAll();
    tickets.sort((ticket1, ticket2) -> Float.compare(ticket1.getPrice(), ticket2.getPrice()));

    for (Ticket ticket : tickets) {
      printer.println(Float.toString(ticket.getPrice()));
    }

    return true;
  }
}
