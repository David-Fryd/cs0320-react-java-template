package edu.brown.cs.student.repl;

import static edu.brown.cs.student.repl.REPL.parseInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DumbREPL {
  public DumbREPL() {}

  public void run() {
    System.out.println("Dumb REPL started");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      // parse user input
      while ((input = br.readLine()) != null) {
        List<String> parsedInput = parseInput(input);
        switch (parsedInput.get(0)) {
          case "load":
            System.out.println("Dumb repl fr");
            break;
          case "exit":
            return;
          default:
            System.err.println("ERROR: Invalid command");
            break;
        }
      }
    } catch (IOException ex) {
      System.err.println("ERROR: Error reading input.");
    } catch (Exception ex) {
      System.err.println("ERROR: Invalid input for REPL");
    }
  }
}
