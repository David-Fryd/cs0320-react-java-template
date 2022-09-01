package edu.brown.cs.student.csv;

import java.util.ArrayList;
import java.util.List;

public class CreatorFailureException extends Exception {
  final List<String> row;

  public CreatorFailureException(String message, List<String> row) {
    super(message);
    this.row = new ArrayList<>(row);
  }
}
