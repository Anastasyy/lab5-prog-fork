package com.megateam.lab.server.executors;

import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.ExecutionException;
import com.megateam.lab.common.exceptions.impl.DefaultExecutorUsedException;
import com.megateam.lab.common.util.Exchange;
import java.util.List;

public interface Executor {

  default boolean execute(Exchange exchange)
      throws ExecutionException, EnvException, DatabaseException {
    throw new DefaultExecutorUsedException(
        "Default execute method had been invoked. It is not possible.");
  }

  default boolean execute(List<Exchange> script) throws ExecutionException {
    throw new DefaultExecutorUsedException(
        "Default execute method had been invoked. It is not possible.");
  }
}
