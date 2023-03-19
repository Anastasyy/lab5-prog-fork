package com.megateam.lab.common.command;

import com.megateam.lab.common.command.impl.*;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.impl.CommandNotFoundException;
import com.megateam.lab.common.util.Printer;
import java.util.List;

public class CommandFactory {
  public static Command newCommand(
      Printer printer,
      CommandSource source,
      String commandString,
      List<String> args,
      Dao<Ticket> dao)
      throws CommandNotFoundException {
    Command command =
        switch (commandString) {
            //			help
          case "info" -> new InfoCommand(printer, source, dao);
          case "show" -> new ShowCommand(printer, source, dao);
          case "add" -> new AddCommand(printer, source, dao);
            //			update id {element}
            //			remove_by_id id
          case "clear" -> new ClearCommand(printer, source, dao);
          case "save" -> new SaveCommand(printer, source, dao);
            //			execute_script script
          case "exit" -> new ExitCommand(printer, source);
          case "remove_first" -> new RemoveFirstCommand(printer, source, dao);
          case "remove_last" -> new RemoveLastCommand(printer, source, dao);
            //			remove_lower {element}
            //			remove_any_by_refundable refundable
            //			filter_less_than_type type
            //			print_field_descending_price

          default -> throw new CommandNotFoundException("Command not found.");
        };

    return command;
  }
}
