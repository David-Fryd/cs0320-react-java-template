package edu.brown.cs.student.repl;

import java.util.HashMap;
import java.util.List;

/** Interface to define a program runnable by a REPL. */
public interface Program {
  /**
   * Program must be able to run a command w/ given arguments.
   *
   * @param commandName specific command name within the program to run.
   * @param arguments List of arguments to supply to the command.
   */
  void run(String commandName, List<String> arguments);

  /**
   * Getter to return the stored Commands of a Program.
   *
   * @return HashMap representing stored Commands.
   */
  HashMap<String, Command> getCommands();
}
