package edu.brown.cs.student.api;

// api exception extends exception
public class APIException extends Exception {
  // constructor
  public APIException(String message) {
    super(message);
  }
}
