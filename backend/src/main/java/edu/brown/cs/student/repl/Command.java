package edu.brown.cs.student.repl;

import java.util.List;

/** Interface to define a command within a Program. */
public interface Command {
  /**
   * Command must have method named execute.
   *
   * @param arguments List of Strings representing additional arguments for the command.
   * @return Object that represents any output of the command.
   */
  Object execute(List<String> arguments);
}
