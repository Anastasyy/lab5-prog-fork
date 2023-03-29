package com.megateam.lab.common.command.impl;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.CommandSource;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.util.Printer;
import lombok.Builder;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class HelpCommand extends Command
{
	@Builder(setterPrefix = "set")
	public HelpCommand(
			@NonNull Printer printer,
			@NonNull CommandSource source,
			@NonNull List<String> args
	)
	{
		super(printer, source, UsesElements.NOT, args);
	}

	@Override
	public boolean execute() throws EnvException, DatabaseException
	{
		try(
				BufferedReader reader = new BufferedReader(
						new FileReader(
								fileManipulationService.retrieveFileByName("help.txt")
						)
				)
		)
		{
			for(String line : reader.lines().toList())
			{
				printer.println(line);
			}
		}
		catch (IOException e)
		{
			printer.println(e.getMessage());
		}
		return true;
	}
}
