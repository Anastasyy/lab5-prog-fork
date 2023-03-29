package com.megateam.lab.client.cli;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.executors.Executor;
import com.megateam.lab.common.resolvers.Resolver;
import com.megateam.lab.common.util.Printer;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sun.misc.Signal;

@RequiredArgsConstructor
public class Console {
  public static final Set<File> EXECUTED_SCRIPTS = new HashSet<>();

  @NonNull private Resolver resolver;
  @NonNull private Executor executor;
  @NonNull private Scanner scanner;
  @NonNull private Printer printer;

  public void run() {
    DataClassesValidator validator = new DataClassesValidator();
    TicketCLIParser ticketCLIParser = new TicketCLIParser(scanner, validator, printer);

    Signal.handle(
        new Signal("INT"),
        (signal) -> {
          printer.println("\nProgram was interrupted: shutdown...");
          System.exit(0);
        });

    while (true) {
      printer.print("Enter command: ");
      try {
        if (!scanner.hasNextLine()) {
          printer.println("Input stream end has been reached: shutdown...");
          break;
        }

        Exchange exchange = resolver.resolve(scanner.nextLine().trim());
        Command command = exchange.getCommand();

        if (command.getUsesElements() == UsesElements.USES) {
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
