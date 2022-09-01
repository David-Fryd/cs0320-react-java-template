package edu.brown.cs.student.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser<U> {
  private final BufferedReader bReader;
  private final CreatorFromRow<U> objCreator;
  private final ReaderCounts counts;

  public CSVParser(Reader reader, CreatorFromRow<U> objCreator) {
    this.bReader = new BufferedReader(reader);
    this.objCreator = objCreator;
    this.counts = new ReaderCounts();
  }

  public List<U> parse() throws IOException, CreatorFailureException {
    List<U> result = new ArrayList<>();
    String line;
    while ((line = bReader.readLine()) != null) {
      List<String> parsedLine = Arrays.asList(line.split(",")); // could change to be generic
      result.add(objCreator.create(parsedLine));
      this.counts.setColCount(parsedLine.size()); // update column count if valid
      this.counts.incrementWordCount(ReaderCounts.countWords(parsedLine));
      this.counts.incrementCharCount(ReaderCounts.countChars(parsedLine));
    }
    this.counts.setRowCount(result.size()); // update row count
    return result;
  }

  public ReaderCounts getCounts() {
    return counts;
  }
}
