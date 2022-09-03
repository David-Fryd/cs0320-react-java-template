package edu.brown.cs.student.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to store information about the state of the server, such as the currently
 * loaded data.
 *
 * <p>This data can be passed to various command handlers, allowing them to talk to eachother
 * (access/modify shared data).
 */
public class ServerState {
  // This can be any data you want to store
  private Set<String> stringSet;

  public ServerState() {
    this.stringSet = new HashSet<String>();
  }

  /**
   * Attempts to add string to the stringSet
   *
   * @param s the string to add
   * @return true if it was successfully added, false otherwise (if it already existed)
   */
  public boolean addToSet(String s) {
    return stringSet.add(s);
  }

  /**
   * Returns an immutable version of the stringSet
   *
   * @return an immutable version of the stringSet
   */
  public Set<String> getStringSet() {
    return Collections.unmodifiableSet(stringSet);
  }
}
