package com.megateam.lab.common.command;

import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.ExecutionException;
import com.megateam.lab.common.executors.ScriptExecutor;
import com.megateam.lab.common.resolvers.ScriptResolver;
import com.megateam.lab.common.util.FileManipulationService;
import com.megateam.lab.common.util.Printer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public abstract class Command {
  @NonNull protected Printer printer;
  @NonNull protected CommandSource source;
  @NonNull @Getter protected UsesElements usesElements;
  @NonNull protected List<String> args;

  @Setter protected List<Object> additionalArgs;
  @Setter protected Dao<Ticket> dao;
  @Setter protected FileManipulationService fileManipulationService;
  @Setter protected ScriptExecutor scriptExecutor;
  @Setter protected ScriptResolver scriptResolver;

  public abstract boolean execute()
          throws EnvException, DatabaseException, CommandArgumentsException, ExecutionException;
}
