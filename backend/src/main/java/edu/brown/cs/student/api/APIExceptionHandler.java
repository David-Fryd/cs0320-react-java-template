package edu.brown.cs.student.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.PrintWriter;
import java.io.StringWriter;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

/** Handles internal server errors and returns the error in JSON format to the frontend. */
public class APIExceptionHandler implements ExceptionHandler {

  /**
   * Message is the error message.
   * stackTrace contains the stack trace of the error surrounded by <pre> tags for easy front-end display.
   */
  public record ErrorInformation(String message, String stackTrace) {}

  @Override
  public void handle(Exception exception, Request request, Response response) {
    StringWriter stacktrace = new StringWriter();
    try (PrintWriter pw = new PrintWriter(stacktrace)) {
      pw.println("<pre>");
      exception.printStackTrace(pw);
      pw.println("</pre>");
    }
    // create a map to return to the frontend with the error message as well as the full
    // stack trace
    ErrorInformation errorInformation =
        new ErrorInformation(exception.getMessage(), stacktrace.toString());

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<ErrorInformation> requestJsonAdapter = moshi.adapter(ErrorInformation.class);

    response.body(requestJsonAdapter.toJson(errorInformation));
  }
}
