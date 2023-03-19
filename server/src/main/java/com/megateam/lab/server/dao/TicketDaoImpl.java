package com.megateam.lab.server.dao;

import com.megateam.lab.common.dao.Dao;
import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.server.db.Database;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class TicketDaoImpl implements Dao<Ticket> {
  @NonNull private Database<Ticket> database;

  public TicketDaoImpl(Database<Ticket> database) {
    this.database = database;
    try {
      database.load();
    } catch (Exception e) {
      //			If collection automatic loading failed - ignoring
    }
  }

  @Override
  public LocalDateTime getCreationDate() {
    return database.getCreationDate();
  }

  @Override
  public void add(@NonNull Ticket item) {
    database.add(item);
  }

  @Override
  public Optional<Ticket> get(@NonNull Integer id) {
    return database.get(id);
  }

  @Override
  public List<Ticket> getAll() {
    return database.getAll();
  }

  @Override
  public void update(@NonNull Integer id, @NonNull Ticket item) {
    database.update(id, item);
  }

  @Override
  public void delete(@NonNull Integer id) {
    database.delete(id);
  }

  @Override
  public void clear() throws EnvException, DatabaseException {
    database.clear();
  }

  @Override
  public void removeFirst() {
    database.removeFirst();
  }

  @Override
  public void removeLast() {
    database.removeLast();
  }

  @Override
  public void save() throws EnvException, DatabaseException {
    database.save();
  }

  @Override
  public void load() throws EnvException, DatabaseException {
    database.load();
  }

  @Override
  public int size() {
    return database.size();
  }
}
