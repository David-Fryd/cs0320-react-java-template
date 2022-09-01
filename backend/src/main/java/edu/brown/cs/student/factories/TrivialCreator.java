package edu.brown.cs.student.factories;

import edu.brown.cs.student.csv.CreatorFromRow;
import java.util.List;

public class TrivialCreator implements CreatorFromRow<List<String>> {
  public List<String> create(List<String> row) {
    return row;
  }
}
