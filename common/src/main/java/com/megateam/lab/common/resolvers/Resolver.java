package com.megateam.lab.common.resolvers;

import com.megateam.lab.common.command.util.Exchange;
import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.exceptions.impl.DataclassParsingException;
import com.megateam.lab.common.exceptions.impl.DefaultResolverUsedException;
import java.io.File;
import java.util.List;

public interface Resolver {
  default Exchange resolve(String line) throws ResolverException {
    throw new DefaultResolverUsedException(
        "Trying to invoke default resolve method. This is not possible.");
  }

  default List<Exchange> resolve(File script) throws ResolverException, DataclassParsingException
  {
    throw new DefaultResolverUsedException(
        "Trying to invoke default resolve method. This is not possible.");
  }
}
