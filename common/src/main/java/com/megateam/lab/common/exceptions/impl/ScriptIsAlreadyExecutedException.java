package com.megateam.lab.common.exceptions.impl;

import com.megateam.lab.common.exceptions.ExecutionException;

public class ScriptIsAlreadyExecutedException extends ExecutionException {
	public ScriptIsAlreadyExecutedException(String message) {
		super(message);
	}
}
