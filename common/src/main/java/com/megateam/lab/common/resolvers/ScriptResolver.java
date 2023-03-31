package com.megateam.lab.common.resolvers;

import com.megateam.lab.common.command.Command;
import com.megateam.lab.common.command.UsesElements;
import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.exceptions.impl.CommandNotFoundException;
import com.megateam.lab.common.exceptions.impl.DataclassParsingException;
import com.megateam.lab.common.exceptions.impl.ResolvingScriptNotFoundException;
import com.megateam.lab.common.exceptions.impl.ScriptCommandResolvingException;
import com.megateam.lab.common.util.DataclassesScriptParser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ScriptResolver implements Resolver {
	@NonNull
	private Resolver resolver;
	@Override
	public List<Exchange> resolve(File script) throws ResolverException, DataclassParsingException
	{
		List<Exchange> exchanges = new LinkedList<>();

		try {
			BufferedReader scriptReader = new BufferedReader(new FileReader(script));
			Scanner scriptScanner = new Scanner(scriptReader);

			while (scriptScanner.hasNextLine()) {
				try {
					Exchange exchange = resolver.resolve(scriptScanner.nextLine());
					Command command = exchange.getCommand();

					if (command.getUsesElements() == UsesElements.USES) {
						command.setAdditionalArgs(
								List.of(
										DataclassesScriptParser.parseTicket(scriptScanner)
								)
						);
						exchange.setCommand(command);
					}
					exchanges.add(exchange);
				} catch (CommandNotFoundException e) {
					throw new ScriptCommandResolvingException(e.getMessage());
				}
			}

		} catch (IOException e) {
			throw new ResolvingScriptNotFoundException("Error occurred while script execution: " + e.getMessage());
		}

		return exchanges;
	}
}
