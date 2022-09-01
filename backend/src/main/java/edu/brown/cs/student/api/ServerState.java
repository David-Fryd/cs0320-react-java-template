package edu.brown.cs.student.api;

import edu.brown.cs.student.csv.ReaderCounts;
import java.util.List;

public class ServerState {
  private String filename;
  private List<List<String>> loadedCSV;
  private ReaderCounts loadedCounts;

  public ServerState() {
    this.filename = "";
    this.loadedCSV = null;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public List<List<String>> getLoadedCSV() {
    return loadedCSV;
  }

  public void setLoadedCSV(List<List<String>> loadedCSV) {
    this.loadedCSV = loadedCSV;
  }

  public ReaderCounts getLoadedCounts() {
    return loadedCounts;
  }

  public void setLoadedCounts(ReaderCounts loadedCounts) {
    this.loadedCounts = loadedCounts;
  }
}
