package api;

import edu.brown.cs.student.api.AddToSetHandler;
import edu.brown.cs.student.api.GetSetHandler;
import edu.brown.cs.student.api.ServerState;
import org.junit.Before;
import org.junit.BeforeClass;
import spark.Spark;

public class APITest {
  // private static JsonAdapter<List<List<String>>> adapter;

  @BeforeClass
  public static void setupServer() {
    Spark.port(1234);
    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  }

  @Before
  public void setupEndpoints() {
    // FRESH ServerState & handlers for each test!
    ServerState server = new ServerState();
    Spark.get("/addtoset", new AddToSetHandler(server));
    Spark.get("/getset", new GetSetHandler(server));
  }

  // TODO: Write some tests!

}
