package edu.brown.cs.student.stars;

import edu.brown.cs.student.kdtree.KDTreeNode;
import java.util.Comparator;

/** Comparator to sort KDTreeNodes by descending euclidean distance from a targetNode. */
public class DistanceSorter implements Comparator<KDTreeNode> {
  private KDTreeNode targetNode;

  /**
   * Constructs a DistanceSorter using the targetNode for euclidean distance.
   *
   * @param targetNode node by which to compare other nodes by euclidean distance.
   */
  public DistanceSorter(KDTreeNode targetNode) {
    this.targetNode = targetNode;
  }

  /**
   * Compares two KDTreeNodes by their euclidean distance from targetNode.
   *
   * @param o1 first KDTreeNode to be compared.
   * @param o2 second KDTreeNode to be compared.
   * @return integer 1 for o1 less than o2, 0 for o1 equals o2, -1 for o1 greater than o2
   */
  @Override
  public int compare(KDTreeNode o1, KDTreeNode o2) {
    try {
      double o1Distance = this.targetNode.euclideanDistance(o1);
      double o2Distance = this.targetNode.euclideanDistance(o2);
      return Double.compare(o2Distance, o1Distance);
      // sort in descending order by euclidean distance
    } catch (Exception ex) {
      System.err.println("ERROR: KDTreeNodes do not have the same dimensionality.");
    }
    return 0;
  }
}
