package com.megateam.lab.client.cli;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.data.util.DataClassesValidator;
import com.megateam.lab.common.executors.ScriptExecutor;
import com.megateam.lab.common.resolvers.Resolver;
import com.megateam.lab.common.util.Printer;
import com.megateam.lab.server.executors.SingleCommandExecutor;
import java.util.List;
import java.util.Scanner;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sun.misc.Signal;

/**
 * The main class provides an ability to interact with an application
 */
@RequiredArgsConstructor
public class Console {
  /**
   * Contains an instance of Resolver
   */
  @NonNull private Resolver resolver;
  /**
   * Contains an instance of CommandSingleExecutor
   */
  @NonNull private SingleCommandExecutor singleCommandExecutor;
  /**
   * Contains an instance of ScriptExecutor
   */
  @NonNull private ScriptExecutor scriptExecutor;
  /**
   * Contains an instance of Scanner
   */
  @NonNull private Scanner scanner;
  /**
   * Contains an instance of Printer
   */
  @NonNull private Printer printer;

  /**
   * Initiates Command resolving process
   */
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
        command.setScriptExecutor(scriptExecutor);

        if (command.getUsesElements() == UsesElements.USES) {
          command.setAdditionalArgs(List.of(ticketCLIParser.parseTicket()));
        }
        exchange.setCommand(command);
        singleCommandExecutor.execute(exchange);
      } catch (Exception e) {
        printer.println(e.getMessage());
      }
    }
  }
}
