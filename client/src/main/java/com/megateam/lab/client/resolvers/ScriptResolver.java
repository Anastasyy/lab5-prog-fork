package com.megateam.lab.client.resolvers;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandFactory;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.exceptions.impl.ResolvingScriptNotFoundException;
import com.megateam.lab.common.resolvers.Resolver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScriptResolver implements Resolver {
  @NonNull private CommandFactory commandFactory;

  @Override
  public List<Exchange> resolve(File script) throws ResolverException {
    List<Exchange> exchanges = new LinkedList<>();

    try {
      List<String> scriptLines = (new BufferedReader(new FileReader(script))).lines().toList();
      int idx = 0;

      for (String commandLine : scriptLines) {
        String[] splittedCommandLine = commandLine.trim().split(" ");
        List<String> commandArgs =
            Arrays.asList(Arrays.copyOfRange(splittedCommandLine, 1, splittedCommandLine.length));

        Command command =
            commandFactory.newCommand(splittedCommandLine[0], CommandSource.SCRIPT, commandArgs);

        if (command.getUsesElements() == UsesElements.USES) {}

        //				try {
        exchanges.add(Exchange.builder().setCommand(command).setArgs(commandArgs).build());
        //				} catch (CommandNotFoundException e) {

        //				}
      }

    } catch (FileNotFoundException e) {
      throw new ResolvingScriptNotFoundException("Resolving script not found.");
    }

    return exchanges;
  }
}
