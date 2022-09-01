package edu.brown.cs.student.repl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Class for a generic command-line read-eval-print-loop (REPL) that runs specific programs. */
public class REPL implements Runnable {
  private Program currentProgram;

  /**
   * Constructs a command-line REPL that runs the given program.
   *
   * @param program Program to be run by the REPL.
   */
  public REPL(Program program) {
    this.currentProgram = program;
  }

  /** Runs the REPL by parsing user input and running a Program's given commands. */
  public void run() {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      // parse user input
      while ((input = br.readLine()) != null) {
        List<String> parsedInput = parseInput(input);

        HashMap<String, Command> commands = this.currentProgram.getCommands();
        if (commands.containsKey(parsedInput.get(0))) {
          // if the inputted command exists, run the command
          List<String> arguments = parsedInput.subList(1, parsedInput.size());
          String currentCommandName = parsedInput.get(0);
          this.currentProgram.run(currentCommandName, arguments);
        } else if (input.equals("EXIT")) {
          // baked-in command to exit the repl
          return;
        } else {
          System.err.println("ERROR: Invalid command.");
        }
      }
    } catch (IOException ex) {
      System.err.println("ERROR: Error reading input.");
    } catch (Exception ex) {
      System.err.println("ERROR: Invalid input for REPL");
    }
  }

  /**
   * Parses user input through regex.
   *
   * @param input String of user input to be parsed.
   * @return split input at whitespaces except within quotes or apostrophes.
   */
  public static List<String> parseInput(String input) {
    List<String> parsedInput = new ArrayList<>();
    // regex parsing adapted from: https://stackoverflow.com/q/366202/
    Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
    Matcher regexMatcher = regex.matcher(input);
    while (regexMatcher.find()) {
      if (regexMatcher.group(1) != null) {
        // Add double-quoted string with the quotes
        parsedInput.add("\"" + regexMatcher.group(1) + "\"");
      } else {
        // Add unquoted word
        parsedInput.add(regexMatcher.group());
      }
    }
    return parsedInput;
  }
}
