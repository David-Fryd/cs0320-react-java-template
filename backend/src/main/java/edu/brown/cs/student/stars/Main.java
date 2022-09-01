package edu.brown.cs.student.stars;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.api.ServerState;
import edu.brown.cs.student.csv.CSVParser;
import edu.brown.cs.student.factories.TrivialCreator;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // DumbREPL repl = new DumbREPL();
    // repl.run();
    // REPL repl = new REPL(new NoOpProgram());
    // repl.run();
    /* here is some spark boilerplate */
    Spark.port(1234);
    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    ServerState state = new ServerState();
    Spark.get("/loadcsv", new LoadCSVHandler(state));
    Spark.get("/getcsv", new GetCSVHandler(state));
  }

  public static class LoadCSVHandler implements Route {
    ServerState state;

    public LoadCSVHandler(ServerState state) {
      this.state = state;
    }

    private boolean safePath(String filename) {
      File file = new File(filename);
      return (file.getParent() == null);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
      String fileName = request.queryParams("file");
      System.out.println(fileName);
      // some method to approve the request: only looks in data folder or something
      if (!safePath(fileName)) {
        response.status(400);
        return "Cannot load file: only accepts files without parent directories";
      }
      // only accepts filenames, no paths
      fileName = "data/" + fileName;
      state.setFilename(fileName);
      CSVParser<List<String>> parser =
          new CSVParser<>(new FileReader(fileName), new TrivialCreator());
      state.setLoadedCSV(parser.parse());
      state.setLoadedCounts(parser.getCounts());
      int rows = parser.getCounts().getRowCount();
      int cols = parser.getCounts().getColCount();
      response.status(200);
      return "Loaded " + fileName + " with " + rows + " rows, " + cols + " columns";
    }
  }

  public static class GetCSVHandler implements Route {
    ServerState state;

    public GetCSVHandler(ServerState state) {
      this.state = state;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
      response.status(200);
      Moshi mosh = new Moshi.Builder().build();
      Type listStringString =
          Types.newParameterizedType(
              List.class, Types.newParameterizedType(List.class, String.class));
      JsonAdapter<List<List<String>>> adapter = mosh.adapter(listStringString);
      return adapter.toJson(this.state.getLoadedCSV());
    }
  }
}
