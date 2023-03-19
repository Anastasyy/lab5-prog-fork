package com.megateam.lab.client.cli;

import com.megateam.lab.client.resolvers.Resolver;
import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandUsesElements;
import com.megateam.lab.common.util.Exchange;
import com.megateam.lab.common.util.Printer;
import com.megateam.lab.common.validation.DataClassesValidator;
import com.megateam.lab.server.executors.Executor;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Console {
  public static final Set<File> EXECUTED_SCRIPTS = new HashSet<>();

  @NonNull private Resolver resolver;
  @NonNull Executor executor;
  @NonNull private Scanner scanner;
  @NonNull private Printer printer;

  public void run() {
    DataClassesValidator validator = new DataClassesValidator();
    TicketCLIParser ticketCLIParser = new TicketCLIParser(scanner, validator, printer);
    while (true) {
      printer.print("Enter command: ");
      try {
        if (!scanner.hasNextLine()) {
          printer.println("Input stream end has been reached. Shutdown...");
          break;
        }

        Exchange exchange = resolver.resolve(scanner.nextLine().trim());
        Command command = exchange.getCommand();

        if (command.getUsesElements() == CommandUsesElements.USES) {
          command.setAdditionalArgs(List.of(ticketCLIParser.parseTicket()));
          exchange.setCommand(command);
        }
        executor.execute(exchange);
      } catch (Exception e) {
        printer.println(e.getMessage());
      }
    }
  }
}
