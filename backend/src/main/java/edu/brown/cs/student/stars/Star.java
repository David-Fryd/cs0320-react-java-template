package edu.brown.cs.student.stars;

import edu.brown.cs.student.kdtree.KDTreeNode;
import java.util.Arrays;
import java.util.Objects;

/** Class to represent a Star, which has an associated ID, name, and point. */
public class Star implements KDTreeNode {
  private int id;
  private String name;
  private double[] point;
  private final int hashNum = 31;

  /**
   * Constructs a star with the given ID, name, and point.
   *
   * @param id ID of the star
   * @param name name of the star
   * @param point point in space that the star occupies
   */
  public Star(int id, String name, double[] point) {
    this.name = name;
    this.id = id;
    this.point = point;
  }

  /**
   * Getter to return name of the star.
   *
   * @return String name of the star
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter to return id of the star.
   *
   * @return int id of the star
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter to return point for the star.
   *
   * @return Point point of the star
   */
  public double[] getPoint() {
    return this.point;
  }

  /**
   * Getter to return dimension of point.
   *
   * @return int dimension of the point
   */
  public int getDimension() {
    return this.point.length;
  }

  /**
   * Finds euclidean distance between the current node and a given node.
   *
   * @param node The point to find the distance to.
   * @return double The straight line distance.
   */
  public double euclideanDistance(KDTreeNode node) {
    double[] currentVals = this.point;
    double[] targetVals = node.getPoint();
    double sum = 0;
    for (int i = 0; i < currentVals.length; i++) {
      sum += Math.pow(currentVals[i] - targetVals[i], 2);
    }
    return Math.sqrt(sum);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Star)) {
      return false;
    }
    Star star = (Star) o;
    return id == star.id && Objects.equals(name, star.name) && Arrays.equals(point, star.point);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, name);
    result = hashNum * result + Arrays.hashCode(point);
    return result;
  }
}
