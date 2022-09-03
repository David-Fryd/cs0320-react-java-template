package edu.brown.cs.student.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.List;
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
  // (We have a similar contract that the response is of form stringSetAsList: string[], message: string) )
  public record AddStringResponseBody(List<String> stringSetAsList, String message) {}

  @Override
  public Object handle(Request request, Response response) throws Exception {
    // Parse the request body into a request object
    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<AddStringRequestBody> requestJsonAdapter =
        moshi.adapter(AddStringRequestBody.class);
    AddStringRequestBody requestBody = requestJsonAdapter.fromJson(request.body());

    // TODO: Add error handling above

    System.out.println("reqbody key: " + requestBody.key);
    System.out.println("(raw request body): " + request.body());

    // TODO: Replace with backend logic connecting with serverstate
    ArrayList<String> demo = new ArrayList<String>();
    demo.add("hello");
    demo.add("world");

    // Construct the contents of the response
    AddStringResponseBody responseBody =
        new AddStringResponseBody(demo, "some informative message");

    // Turn the response into JSON and return it (to the frontend)
    JsonAdapter<AddStringResponseBody> responseJsonAdapter =
        moshi.adapter(AddStringResponseBody.class);
    String jsonResponseBody = responseJsonAdapter.toJson(responseBody);
    response.status(200); // Informs the frontend that the request was successful
    System.out.println("Returning: " + jsonResponseBody);
    return jsonResponseBody;
  }
}
