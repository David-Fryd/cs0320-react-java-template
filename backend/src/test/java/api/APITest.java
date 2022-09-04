package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Before;
import org.junit.BeforeClass;

import edu.brown.cs.student.api.AddToSetHandler;
import edu.brown.cs.student.api.GetSetHandler;
import edu.brown.cs.student.api.ServerState;
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
