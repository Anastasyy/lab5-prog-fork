package com.megateam.lab.common.executors;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.ExecutionException;
import com.megateam.lab.common.exceptions.impl.ExectionFailedException;
import com.megateam.lab.common.exceptions.impl.ScriptIsAlreadyExecutedException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class provides execution of several commands at the same time
 */
@RequiredArgsConstructor
public class ScriptExecutor implements Executor {

  /**
   * Contains all the executed scripts
   */
   public static final Set<String> EXECUTED_SCRIPTS = new HashSet<>();

  /**
   * Contains a SingleCommandExecutor inside for command execution
   */
  @NonNull private Executor executor;

  /**
   * This method executes commands from the exchanges list
   * @param script contains an exchanges list to be executed
   * @return boolean as an execution status
   * @throws ExecutionException if the group of commands had been already executed
   * or if some unexpected exception was caught
   */
  @Override
  public boolean execute(List<Exchange> script) throws ExecutionException {
    for (Exchange commandExchange : script) {
      try {
        Command command = commandExchange.getCommand();
        command.setScriptExecutor(this);
        commandExchange.setCommand(command);
        executor.execute(commandExchange);
      } catch (ScriptIsAlreadyExecutedException e) {
        throw new ExectionFailedException(e.getMessage());
      } catch (Exception e) {
        throw new ExectionFailedException("Script command execution failed: " + e.getMessage());
      }
    }

    return true;
  }
}
