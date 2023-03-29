package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.util.TypesParser;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.InvalidAmountOfArgumentsException;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class RemoveByIdCommand extends Command
{
	@Builder(setterPrefix = "set")
	public RemoveByIdCommand(
			@NonNull Printer printer,
			@NonNull CommandSource source,
			@NonNull List<String> args
	) {
		super(printer, source, UsesElements.NOT, args);
	}

	@Override
	public boolean execute() throws EnvException, DatabaseException, CommandArgumentsException
	{
		if (args.size() != 1) {
			throw new InvalidAmountOfArgumentsException(
					"Command consumes 1 argument. Provided: " + args.size()
			);
		}
		dao.remove(TypesParser.parseInteger(args.get(0)));
		printer.println("Successfully removed element.");
		return true;
	}
}
