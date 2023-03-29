package com.megateam.lab.client.resolvers;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandFactory;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.resolvers.Resolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SingleCommandResolver implements Resolver
{
  @NonNull private CommandFactory commandFactory;

  @Override
  public Exchange resolve(String line) throws ResolverException {
    String[] sepLine = line.split(" ");
    List<String> args = List.of(Arrays.copyOfRange(sepLine, 1, sepLine.length));
    Command command = commandFactory.newCommand(sepLine[0], CommandSource.CONSOLE, args);

    return Exchange.builder().setCommand(command).setArgs(args).build();
  }
}
