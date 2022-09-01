package edu.brown.cs.student.stars;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.repl.REPL;
import java.util.List;
import org.junit.Test;

public class REPLTest {
  /** * Tests parsing input. */
  @Test
  public void testParseInput() {
    String input = "neighbors 10 \"Colton's Star\" 'Whoops! Invalid'";
    List<String> parsedInput = REPL.parseInput(input);

    assertEquals(5, parsedInput.size());
    assertEquals("neighbors", parsedInput.get(0));
    assertEquals("10", parsedInput.get(1));
    assertEquals("\"Colton's Star\"", parsedInput.get(2));
    assertEquals("Whoops!", parsedInput.get(3));
    assertEquals("Invalid", parsedInput.get(4));
  }
}
