package edu.brown.cs.student.csv;

import java.util.List;

public interface CreatorFromRow<T> {
  T create(List<String> row) throws CreatorFailureException;
}
