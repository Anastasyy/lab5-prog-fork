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

@RequiredArgsConstructor
public class ScriptExecutor implements Executor {

	@NonNull private Executor executor;
	public static final Set<String> EXECUTED_SCRIPTS = new HashSet<>();

	@Override
	public boolean execute(List<Exchange> script) throws ExecutionException
	{
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
