package com.megateam.lab.server.executors;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Exchange;

public class SingleCommandExecutor implements Executor {
  @Override
  public boolean execute(Exchange exchange) throws EnvException, DatabaseException {
    Command command = exchange.getCommand();
    return command.execute();
  }
}
