package com.megateam.lab.server.executors;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.ExecutionException;
import com.megateam.lab.common.executors.Executor;
import com.megateam.lab.common.resolvers.ScriptResolver;
import com.megateam.lab.common.util.FileManipulationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SingleCommandExecutor implements Executor
{

	@NonNull Dao<Ticket> dao;
	@NonNull FileManipulationService fileManipulationService;
	@NonNull ScriptResolver scriptResolver;

	@Override
	public boolean execute(Exchange exchange)
			throws EnvException, DatabaseException, CommandArgumentsException, ExecutionException
	{
		Command command = exchange.getCommand();

		command.setDao(dao);
		command.setFileManipulationService(fileManipulationService);
		command.setScriptResolver(scriptResolver);

		return command.execute();
	}
}
