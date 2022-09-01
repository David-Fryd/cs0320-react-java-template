package edu.brown.cs.student.csv;

import java.util.List;

public class ReaderCounts {
  private int wordCount, charCount, rowCount, colCount;

  public ReaderCounts() {
    wordCount = 0;
    charCount = 0;
    rowCount = 0;
    colCount = 0;
  }

  public void printCounts() {
    System.out.println("Words: " + wordCount);
    System.out.println("Characters: " + charCount);
    System.out.println("Rows: " + rowCount);
    System.out.println("Columns: " + colCount);
  }

  public int getWordCount() {
    return wordCount;
  }

  public void setWordCount(int wordCount) {
    this.wordCount = wordCount;
  }

  public void incrementWordCount(int increment) {
    this.wordCount += increment;
  }

  public int getCharCount() {
    return charCount;
  }

  public void setCharCount(int charCount) {
    this.charCount = charCount;
  }

  public void incrementCharCount(int increment) {
    this.wordCount += increment;
  }

  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public int getColCount() {
    return colCount;
  }

  public void setColCount(int colCount) {
    this.colCount = colCount;
  }

  public static int countWords(List<String> parsedLine) {
    int words = 0;
    for (String keyword : parsedLine) {
      String[] splitArr = keyword.split(" ");
      words += splitArr.length;
    }
    return words;
  }

  public static int countChars(List<String> parsedLine) {
    int words = 0;
    for (String keyword : parsedLine) {
      words += keyword.length();
    }
    return words;
  }
}
