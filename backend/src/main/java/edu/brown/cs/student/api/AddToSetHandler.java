package edu.brown.cs.student.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddToSetHandler implements Route {

  // The current state of the server
  private ServerState state;

  public AddToSetHandler(ServerState state) {
    this.state = state;
  }

  /**
   * A record is just a class that does nothing except hold data. In this case, it defines the
   * expected structure of the request body we receieve from the frontend.
   *
   * <p>We define this class so Moshi knows how to parse the request's body into usable data.
   *
   * <p>We have a contract with the frontend to send us a body that contains a 'key' and a 'value'
   * field. We will add the value to the set.
   */
  public record AddStringRequestBody(String key) {}
  /** We also have a structure to define our response to the frontend. */
  public record AddStringResponseBody(String message) {}

  @Override
  public Object handle(Request request, Response response) throws Exception {

    // Set up the adapters we need for reading the request and writing the response
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<AddStringRequestBody> requestJsonAdapter =
        moshi.adapter(AddStringRequestBody.class);
    JsonAdapter<AddStringResponseBody> responseJsonAdapter =
        moshi.adapter(AddStringResponseBody.class);

    // Parse the request body into a request object
    AddStringRequestBody requestBody = requestJsonAdapter.fromJson(request.body());
    // TODO: Add error handling for IOException & JsonDataException for badly formatted requests

    // DEBUG:
    System.out.println("Server (addtoset) receieved request with body: " + request.body());

    // Some example checks for improper client requests
    if (requestBody.key == "") {
      response.status(400); // Indicate bad request
      System.out.println("Server (addtoset) sending error back to client: Empty string"); // DEBUG
      return responseJsonAdapter.toJson(
          new AddStringResponseBody(
              "I have decided for an arbitrary reason not to allow the empty string!"));
    }
    if (!this.state.addToSet(requestBody.key)) {
      response.status(400); // Bad request
      System.out.println(
          "Server (addtoset) sending error back to client: Key already exists"); // DEBUG
      throw new APIException("ERROR: Key \"" + requestBody.key + "\"already exists");
    }

    // Construct the contents of the response
    AddStringResponseBody responseBody =
        new AddStringResponseBody(requestBody.key + " added to the string list");

    // Turn the response into JSON and return it (to the frontend)
    // responseJsonAdapter.toJson(responseBody);
    response.status(200); // Indicate request was successful
    System.out.println(
        "Server (addtoset) responding with body: "
            + responseJsonAdapter.toJson(responseBody)); // DEBUG
    return responseJsonAdapter.toJson(responseBody);
  }
}
