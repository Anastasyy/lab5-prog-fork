package com.megateam.lab.client.resolvers;

import com.megateam.lab.common.exceptions.ResolverException;
import com.megateam.lab.common.exceptions.impl.DefaultResolverUsedException;
import com.megateam.lab.common.util.Exchange;
import java.io.File;
import java.util.List;
import java.util.Optional;

public interface Resolver {
  default Exchange resolve(String line) throws ResolverException {
    throw new DefaultResolverUsedException(
        "Trying to invoke default resolve method. This is not possible.");
  }

  default Optional<List<Exchange>> resolve(File script) throws ResolverException {
    throw new DefaultResolverUsedException(
        "Trying to invoke default resolve method. This is not possible.");
  }
}
