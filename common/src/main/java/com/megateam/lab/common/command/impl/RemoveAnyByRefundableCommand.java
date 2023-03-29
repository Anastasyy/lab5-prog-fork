package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.util.TypesParser;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.CommandArgumentsException;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.InvalidAmountOfArgumentsException;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.util.Iterator;
import java.util.List;

public class RemoveAnyByRefundableCommand extends Command {
	@Builder(setterPrefix = "set")
	public RemoveAnyByRefundableCommand(
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

		List<Ticket> tickets = dao.getAll();
		Iterator<Ticket> iterator = tickets.iterator();

		while (iterator.hasNext()) {
			Ticket ticket = iterator.next();

			if (ticket.getRefundable().booleanValue() == TypesParser.parseBoolean(args.get(0))) {
				iterator.remove();
				printer.println("Successfully removed element.");
				return true;
			}
		}

		return false;
	}
}
