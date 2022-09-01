package cvs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.brown.cs.student.csv.CSVParser;
import edu.brown.cs.student.csv.CreatorFailureException;
import edu.brown.cs.student.csv.ReaderCounts;
import edu.brown.cs.student.factories.TrivialCreator;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CSVTest {
  @Test
  public void TestSimple() throws IOException, CreatorFailureException {
    TrivialCreator factory = new TrivialCreator();
    CSVParser<List<String>> parser = new CSVParser<>(new FileReader("data/dummy.csv"), factory);
    List<List<String>> result = parser.parse();
    ReaderCounts cnts = parser.getCounts();
    assertEquals(cnts.getRowCount(), 4);
    assertEquals(cnts.getColCount(), 3);
    List<String> expected = Arrays.asList("hi", "hi", "hi");
    assertTrue(listEquals(result.get(0), expected));
  }

  private <T> boolean listEquals(List<T> l1, List<T> l2) {
    if (l1.size() != l2.size()) {
      return false;
    }
    for (int i = 0; i < l1.size(); i++) {
      if (!l1.get(i).equals(l2.get(i))) {
        return false;
      }
    }
    return true;
  }
}
