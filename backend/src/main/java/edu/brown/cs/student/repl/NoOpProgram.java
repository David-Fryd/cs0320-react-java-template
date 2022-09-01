package edu.brown.cs.student.repl;

import java.util.HashMap;
import java.util.List;

public class NoOpProgram implements Program {
  @Override
  public void run(String commandName, List<String> arguments) {}

  @Override
  public HashMap<String, Command> getCommands() {
    return new HashMap<>();
  }
}
