package com.megateam.lab.common.resolvers;

import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.ResolverException;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ScriptResolver implements Resolver {
	@Override
	public List<Exchange> resolve(File script) throws ResolverException {
		return Collections.emptyList();
	}



}
