package com.megateam.lab.client;

import com.megateam.lab.client.cli.Console;
import com.megateam.lab.client.resolvers.Resolver;
import com.megateam.lab.client.resolvers.SingleCommandResolver;
import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.util.ConsolePrinter;
import com.megateam.lab.common.util.Printer;
import com.megateam.lab.server.dao.TicketDaoImpl;
import com.megateam.lab.server.db.*;
import com.megateam.lab.server.executors.Executor;
import com.megateam.lab.server.executors.SingleCommandExecutor;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    FileManipulationService fileManipulationService = new FileManipulationService();
    FileValidationService fileValidationService = new FileValidationService();
    Scanner scanner = new Scanner(System.in);
    Printer printer = new ConsolePrinter();

    TicketMarshallingUnmarshallingService marUnmarService =
        new TicketMarshallingUnmarshallingService(fileManipulationService, fileValidationService);
    Database<Ticket> database = new TicketDatabase(marUnmarService);
    Dao<Ticket> dao = new TicketDaoImpl(database);

    Resolver resolver = new SingleCommandResolver(printer, dao);
    Executor executor = new SingleCommandExecutor();

    Console console = new Console(resolver, executor, scanner, printer);

    console.run();
  }
}
