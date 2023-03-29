package com.megateam.lab.server.executors;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.executors.Executor;
import com.megateam.lab.common.util.FileManipulationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SingleCommandExecutor implements Executor {

  @NonNull Dao<Ticket> dao;
  @NonNull FileManipulationService fileManipulationService;

  @Override
  public boolean execute(Exchange exchange)
      throws EnvException, DatabaseException, CommandArgumentsException {
    Command command = exchange.getCommand();

    command.setDao(dao);
    command.setFileManipulationService(fileManipulationService);

    return command.execute();
  }
}
