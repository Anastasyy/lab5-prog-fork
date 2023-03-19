package com.megateam.lab.common.command;

import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Command {
  @NonNull protected Printer printer;
  @NonNull protected CommandSource source;
  @NonNull @Getter protected CommandUsesElements usesElements;
  @NonNull protected List<String> args;
  @Setter protected List<Object> additionalArgs;

  public abstract boolean execute() throws EnvException, DatabaseException;
}
