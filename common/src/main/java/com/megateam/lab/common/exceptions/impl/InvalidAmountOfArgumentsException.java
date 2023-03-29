package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.CommandArgumentsException;

public class InvalidAmountOfArgumentsException extends CommandArgumentsException {
	public InvalidAmountOfArgumentsException(String message)
	{
		super(message);
	}
}
