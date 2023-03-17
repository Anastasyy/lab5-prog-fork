package com.megateam.lab.server.db;

import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Database<T>
{
	LocalDateTime getCreationDate();
	Optional<T> get(@NonNull Integer id);
	List<T> getAll();
	void add(T item);
	void update(@NonNull Integer id, @NonNull T item);
	void delete(Integer id);
	void clear() throws EnvException, DatabaseException;
	void removeFirst();
	void removeLast();
	void save() throws EnvException, DatabaseException;
	void load() throws EnvException, DatabaseException;

}
