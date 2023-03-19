package com.megateam.lab.client.resolvers;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandFactory;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.util.Exchange;
import com.megateam.lab.common.util.Printer;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SingleCommandResolver implements Resolver {
  @NonNull private Printer printer;
  @NonNull private Dao<Ticket> dao;

  @Override
  public Exchange resolve(String line) throws ResolverException {
    String[] sepLine = line.split(" ");
    List<String> args = List.of(Arrays.copyOfRange(sepLine, 1, sepLine.length));
    Command command =
        CommandFactory.newCommand(printer, CommandSource.CONSOLE, sepLine[0], args, dao);

    Exchange exchange = Exchange.builder().setCommand(command).setArgs(args).build();

    return exchange;
  }
}
