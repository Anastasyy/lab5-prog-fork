package com.megateam.lab.common.dao;

import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public interface Dao<T> {
  LocalDateTime getCreationDate();

  void add(@NonNull T item);

  Optional<T> get(@NonNull Integer id);

  List<T> getAll();

  void update(@NonNull Integer id, @NonNull T item);

  void delete(@NonNull Integer id);

  void clear() throws EnvException, DatabaseException;

  void removeFirst();

  void removeLast();

  void save() throws EnvException, DatabaseException;

  void load() throws EnvException, DatabaseException;

  int size();
}
