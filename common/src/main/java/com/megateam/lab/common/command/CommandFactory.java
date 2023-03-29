package com.megateam.lab.common.command;

import com.megateam.lab.common.command.impl.*;
import com.megateam.lab.common.exceptions.impl.CommandNotFoundException;
import com.megateam.lab.common.util.Printer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommandFactory {
    @NonNull private Printer printer;
  public Command newCommand(
          @NonNull String commandString,
          @NonNull CommandSource source,
          @NonNull List<String> args
  ) throws CommandNotFoundException {
      Command command =
              switch (commandString) {
                    case "help" -> HelpCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "info" -> InfoCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "show" -> ShowCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "add" -> AddCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "update" -> UpdateCommand.builder()
		                    .setPrinter(printer)
		                    .setSource(source)
		                    .setArgs(args)
		                    .build();

                    case "remove_by_id" -> RemoveByIdCommand.builder()
                          .setPrinter(printer)
                          .setSource(source)
                          .setArgs(args)
                          .build();

                    case "clear" -> ClearCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "save" -> SaveCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

//                    TODO: case "execute_script"

                    case "exit" -> ExitCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "remove_first" -> RemoveFirstCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "remove_last" -> RemoveLastCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "remove_lower" -> RemoveLowerCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                  case "remove_any_by_refundable" -> RemoveAnyByRefundableCommand.builder()
                          .setPrinter(printer)
                          .setSource(source)
                          .setArgs(args)
                          .build();

                    case "print_field_descending_price" -> PrintFieldDescendingPriceCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    case "filter_less_than_type" -> FilterLessThanTypeCommand.builder()
                            .setPrinter(printer)
                            .setSource(source)
                            .setArgs(args)
                            .build();

                    default -> throw new CommandNotFoundException("Command not found.");
              };

    return command;
  }
}
