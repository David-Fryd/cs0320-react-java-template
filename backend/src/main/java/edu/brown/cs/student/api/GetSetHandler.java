package edu.brown.cs.student.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetSetHandler implements Route {

  // The current state of the server
  private ServerState state;

  public GetSetHandler(ServerState state) {
    this.state = state;
  }

  /**
   * A record is just a class that does nothing except hold data. In this case, it defines the
   * structure of the response body we send to the frontend.
   *
   * <p>We define this class so Moshi knows how to parse the request's body into usable data.
   */
  public record GetSetResponseBody(List<String> stringSetAsList, String message) {}

  @Override
  public Object handle(Request request, Response response) throws Exception {
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<GetSetResponseBody> responseJsonAdapter = moshi.adapter(GetSetResponseBody.class);

    // Convert the set into a list because JSON doesn't support sets
    List<String> stringSetAsList = new ArrayList<>(this.state.getStringSet());

    // DEBUG
    System.out.println("Server (getset) receieved request.");

    // Return the response
    response.status(200); // Indicate request was successful
    System.out.println(
        "Server (getset) responding with body:"
            + responseJsonAdapter.toJson(
                new GetSetResponseBody(stringSetAsList, "Set returned as list")));
    return responseJsonAdapter.toJson(
        new GetSetResponseBody(stringSetAsList, "Set returned as list"));
  }
}
