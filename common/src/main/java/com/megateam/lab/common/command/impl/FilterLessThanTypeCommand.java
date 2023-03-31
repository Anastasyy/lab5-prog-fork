package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.data.TicketType;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.InvalidAmountOfArgumentsException;
import com.megateam.lab.common.util.Printer;
import com.megateam.lab.common.util.TypesParser;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;

public class FilterLessThanTypeCommand extends Command {
  @Builder(setterPrefix = "set")
  public FilterLessThanTypeCommand(
      @NonNull Printer printer, @NonNull CommandSource source, @NonNull List<String> args) {
    super(printer, source, UsesElements.NOT, args);
  }

  @Override
  public boolean execute() throws EnvException, DatabaseException, CommandArgumentsException {

    if (args.size() != 1) {
      throw new InvalidAmountOfArgumentsException(
          "Command consumes 1 argument. Provided: " + args.size());
    }

    TicketType type = TypesParser.parseTicketType(args.get(0));
    List<Ticket> tickets = dao.getAll();
    List<Ticket> correctTickets =
        tickets.stream().filter((ticket) -> ticket.getType().compareTo(type) < 0).toList();
    printer.printList(correctTickets);
    return true;
  }
}
