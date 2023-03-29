package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class RemoveLowerCommand extends Command
{
	@Builder(setterPrefix = "set")
	public RemoveLowerCommand(
			@NonNull Printer printer,
			@NonNull CommandSource source,
			@NonNull List<String> args
	) {
		super(printer, source, UsesElements.USES, args);
	}

	@Override
	public boolean execute() throws EnvException, DatabaseException
	{
		Ticket ticket = (Ticket) additionalArgs.get(0);
		List<Ticket> tickets = dao.getAll();
		Integer removedId;
		boolean removed = false;

		for (Ticket ticketPointer : tickets)
		{
			if (ticketPointer.compareTo(ticket) < 0)
			{
				removedId = ticketPointer.getId();
				dao.remove(removedId);
				removed = true;
				printer.println("Removed ticket with ID: " + removedId);
			}
		}

		if (!removed) printer.println("Element, lower than specified one does not exists");
		return true;
	}
}
