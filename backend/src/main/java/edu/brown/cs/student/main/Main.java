package edu.brown.cs.student.main;

import edu.brown.cs.student.api.APIExceptionHandler;
import edu.brown.cs.student.api.AddToSetHandler;
import edu.brown.cs.student.api.GetSetHandler;
import edu.brown.cs.student.api.ServerState;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  // Spark should start on this port by default (unless specified by CLI)
  private static final int DEFAULT_PORT = 3017;
  // Command Line Args stored here
  private final String[] args;

  /**
   * Constructor for Main.
   *
   * @param args will store CLI args
   */
  private Main(String[] args) {
    this.args = args;
  }

  public static void main(String[] args) {
    new Main(args).run();
  }

  /**
   * Parses command line arguments and then runs the server.
   *
   * <p>(Currently the only CLI is manually declaring spark port number)
   */
  private void run() {
    OptionParser parser = new OptionParser();
    parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);

    runSparkServer((int) options.valueOf("port"));
  }

  /**
   * Starts the Spark server, adding all of the necessary endpoints in the process.
   *
   * @param port The port to run the spark server on
   */
  private static void runSparkServer(int port) {
    System.out.println("\033[1mStarting spark server on port " + port + "\033[0m");

    Spark.port(port);

    // The following section tells spark to accept requests from endpoints we added
    // TODO: ^ This is just my understanding, please correct me if I'm wrong
    Spark.options(
        "/*",
        (request, response) -> {
          String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
          if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
          }
          String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
          if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
          }
          return "OK";
        });

    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

    // Shared "serverState" object used by all endpoints that need to access shared data
    ServerState serverState = new ServerState();

    // Add all endpoints here
    Spark.post("/addtoset", new AddToSetHandler(serverState));
    Spark.get("/getset", new GetSetHandler(serverState));
    // ...

    // When there is an error on the API side, this handler will be called
    Spark.exception(Exception.class, new APIExceptionHandler());
  }
}
