package edu.brown.cs.student.stars;

import static org.junit.Assert.*;

import edu.brown.cs.student.kdtree.KDTree;
import edu.brown.cs.student.kdtree.KDTreeNode;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KDTreeTest {
  private KDTree<KDTreeNode> kdTree;
  private KDTree<Star> starsKDTree;

  // Testing simulated user input/output adapted from:
  // https://stackoverflow.com/q/1119385
  // https://stackoverflow.com/q/6415728
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  /** Sets up the k-d Tree using an odd number of 2d points. */
  public void setUpOddPoints() {
    ArrayList<KDTreeNode> points = new ArrayList<>();
    points.add(new Point(new double[] {4, 1}));
    points.add(new Point(new double[] {0, 5}));
    points.add(new Point(new double[] {3, 6}));
    points.add(new Point(new double[] {6, 5}));
    points.add(new Point(new double[] {7, 0}));
    points.add(new Point(new double[] {6, 2}));
    points.add(new Point(new double[] {6, 4}));
    this.kdTree = new KDTree<>(points, 0);
  }

  /** Sets up the k-d Tree using an even number of 2d points. */
  public void setUpEvenPoints() {
    ArrayList<KDTreeNode> points = new ArrayList<>();
    points.add(new Point(new double[] {4, 1}));
    points.add(new Point(new double[] {0, 5}));
    points.add(new Point(new double[] {3, 6}));
    points.add(new Point(new double[] {6, 5}));
    points.add(new Point(new double[] {7, 0}));
    points.add(new Point(new double[] {6, 2}));
    points.add(new Point(new double[] {6, 4}));
    points.add(new Point(new double[] {1, 0}));
    this.kdTree = new KDTree<>(points, 0);
  }

  /** Sets up the k-d Tree using an empty ArrayList of nodes. */
  public void setUpEmptyTree() {
    ArrayList<KDTreeNode> points = new ArrayList<>();
    this.kdTree = new KDTree<>(points, 0);
  }

  /** Sets up input/output streams. */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  /** Resets the k-d Tree. */
  @After
  public void tearDown() {
    this.kdTree = null;
    this.starsKDTree = null;
  }

  /** Restores the output/error streams. */
  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  /** * Tests whether the k-d Tree has the correct root node. */
  @Test
  public void testCorrectRoot() {
    setUpOddPoints();

    assertEquals(2, kdTree.getHead().getDimension());
    assertEquals("[6.0, 5.0]", Arrays.toString(kdTree.getHead().getPoint()));
  }

  /** * Tests whether the k-d Tree's root has the correct children nodes. */
  @Test
  public void testCorrectChildren() {
    setUpOddPoints();

    KDTree<KDTreeNode> left = kdTree.getLeft();
    KDTree<KDTreeNode> right = kdTree.getRight();

    assertEquals(2, left.getHead().getDimension());
    assertEquals(2, right.getHead().getDimension());
    assertEquals("[0.0, 5.0]", Arrays.toString(left.getHead().getPoint()));
    assertEquals("[6.0, 2.0]", Arrays.toString(right.getHead().getPoint()));
  }

  /** * Tests whether the k-d Tree's root's children have the correct children nodes. */
  @Test
  public void testCorrectChildrensChildren() {
    setUpOddPoints();

    KDTree<KDTreeNode> leftLeft = kdTree.getLeft().getLeft();
    KDTree<KDTreeNode> leftRight = kdTree.getLeft().getRight();

    KDTree<KDTreeNode> rightLeft = kdTree.getRight().getLeft();
    KDTree<KDTreeNode> rightRight = kdTree.getRight().getRight();

    assertEquals(2, leftLeft.getHead().getDimension());
    assertEquals(2, leftRight.getHead().getDimension());
    assertEquals(2, rightLeft.getHead().getDimension());
    assertEquals(2, rightRight.getHead().getDimension());

    assertEquals("[4.0, 1.0]", Arrays.toString(leftLeft.getHead().getPoint()));
    assertEquals("[3.0, 6.0]", Arrays.toString(leftRight.getHead().getPoint()));
    assertEquals("[7.0, 0.0]", Arrays.toString(rightLeft.getHead().getPoint()));
    assertEquals("[6.0, 4.0]", Arrays.toString(rightRight.getHead().getPoint()));
  }
  /** * Tests whether the k-d Tree's initializes correctly with an empty input list of nodes. */
  @Test
  public void testEmptyTree() {
    setUpEmptyTree();

    assertNull(kdTree.getHead());
    assertNull(kdTree.getLeft());
    assertNull(kdTree.getRight());
    assertEquals(kdTree.getKdTreeNodes().size(), 0);
    assertEquals(kdTree.getAxis(), 0);
  }

  /** * Tests whether the k-d Tree's initializes correctly an even number of nodes. */
  @Test
  public void testEvenNumNodes() {
    setUpEvenPoints();

    assertEquals(2, kdTree.getHead().getDimension());
    assertEquals("[6.0, 5.0]", Arrays.toString(kdTree.getHead().getPoint()));

    KDTree<KDTreeNode> left = kdTree.getLeft();
    KDTree<KDTreeNode> right = kdTree.getRight();

    assertEquals(2, left.getHead().getDimension());
    assertEquals(2, right.getHead().getDimension());
    assertEquals("[0.0, 5.0]", Arrays.toString(left.getHead().getPoint()));
    assertEquals("[6.0, 2.0]", Arrays.toString(right.getHead().getPoint()));

    KDTree<KDTreeNode> leftLeft = kdTree.getLeft().getLeft();
    KDTree<KDTreeNode> leftRight = kdTree.getLeft().getRight();
    KDTree<KDTreeNode> rightLeft = kdTree.getRight().getLeft();
    KDTree<KDTreeNode> rightRight = kdTree.getRight().getRight();

    assertEquals(2, leftLeft.getHead().getDimension());
    assertEquals(2, leftRight.getHead().getDimension());
    assertEquals(2, rightLeft.getHead().getDimension());
    assertEquals(2, rightRight.getHead().getDimension());

    assertEquals("[4.0, 1.0]", Arrays.toString(leftLeft.getHead().getPoint()));
    assertEquals("[3.0, 6.0]", Arrays.toString(leftRight.getHead().getPoint()));
    assertEquals("[7.0, 0.0]", Arrays.toString(rightLeft.getHead().getPoint()));
    assertEquals("[6.0, 4.0]", Arrays.toString(rightRight.getHead().getPoint()));

    assertEquals("[1.0, 0.0]", Arrays.toString(leftLeft.getLeft().getHead().getPoint()));
  }

  /** * Tests nearest neighbors and radius search on an empty k-d tree. */
  @Test
  public void testEmptyTreeSearch() {
    setUpEmptyTree();

    Point targetPoint = new Point(new double[] {4, 1});
    PriorityQueue<KDTreeNode> neighbors =
        kdTree.kdTreeSearch(
            "neighbors", 5, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> radius =
        kdTree.kdTreeSearch(
            "radius", 5, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());

    assertEquals(neighbors.size(), 0);
    assertEquals(radius.size(), 0);
  }

  /** * Tests nearest neighbors search with k = 0. */
  @Test
  public void testKIs0() {
    setUpEvenPoints();

    Point targetPoint = new Point(new double[] {4, 1});
    PriorityQueue<KDTreeNode> neighbors =
        kdTree.kdTreeSearch(
            "neighbors", 0, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> neighborsNaive =
        kdTree.naiveSearch("neighbors", 0, targetPoint, new HashSet<>());

    assertEquals(neighbors.size(), 0);
    assertArrayEquals(neighborsNaive.toArray(), neighbors.toArray());
  }

  /** * Tests radius search with r = 0 and a point present at targetPoint. */
  @Test
  public void testRIs0AtPoint() {
    setUpEvenPoints();

    Point targetPoint = new Point(new double[] {4, 1});
    PriorityQueue<KDTreeNode> radius =
        kdTree.kdTreeSearch(
            "radius", 0, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> radiusNaive =
        kdTree.naiveSearch("radius", 0, targetPoint, new HashSet<>());

    assertEquals(radius.size(), 1);
    assert radius.peek() != null;
    assertEquals("[4.0, 1.0]", Arrays.toString(radius.peek().getPoint()));
    assertArrayEquals(radiusNaive.toArray(), radius.toArray());
  }

  /** * Tests radius search with r = 0 and no point present at targetPoint. */
  @Test
  public void testRIs0NoPoint() {
    setUpOddPoints();

    Point targetPoint = new Point(new double[] {5, 5});
    PriorityQueue<KDTreeNode> radius =
        kdTree.kdTreeSearch(
            "radius", 0, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> radiusNaive =
        kdTree.naiveSearch("radius", 0, targetPoint, new HashSet<>());

    assertEquals(radius.size(), 0);
    assertArrayEquals(radiusNaive.toArray(), radius.toArray());
  }

  /** * Tests nearest neighbors search with and without a point to ignore. */
  @Test
  public void testNeighborsIgnore() {
    setUpOddPoints();

    Point targetPoint = new Point(new double[] {4, 1});
    HashSet<KDTreeNode> ignore = new HashSet<>();
    ignore.add(targetPoint);

    PriorityQueue<KDTreeNode> neighborsIgnore =
        kdTree.kdTreeSearch("neighbors", 3, targetPoint, new DistanceSorter(targetPoint), ignore);
    PriorityQueue<KDTreeNode> neighborsIgnoreNaive =
        kdTree.naiveSearch("neighbors", 3, targetPoint, ignore);

    assertEquals(3, neighborsIgnore.size());
    assertFalse(neighborsIgnore.contains(targetPoint));
    assertArrayEquals(neighborsIgnoreNaive.toArray(), neighborsIgnore.toArray());
  }

  /** * Tests nearest neighbors search with and without a point to ignore. */
  @Test
  public void testNeighborsNoIgnore() {
    setUpOddPoints();

    Point targetPoint = new Point(new double[] {4, 1});

    PriorityQueue<KDTreeNode> neighborsNoIgnore =
        kdTree.kdTreeSearch(
            "neighbors", 3, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> neighborsNoIgnoreNaive =
        kdTree.naiveSearch("neighbors", 3, targetPoint, new HashSet<>());

    assertEquals(3, neighborsNoIgnore.size());
    assertTrue(neighborsNoIgnore.contains(targetPoint));
    assertArrayEquals(neighborsNoIgnoreNaive.toArray(), neighborsNoIgnore.toArray());
  }

  /** * Tests radius search with a point to ignore. */
  @Test
  public void testRadiusIgnore() {
    setUpOddPoints();

    Point targetPoint = new Point(new double[] {4, 1});
    HashSet<KDTreeNode> ignore = new HashSet<>();
    ignore.add(targetPoint);

    PriorityQueue<KDTreeNode> radiusIgnore =
        kdTree.kdTreeSearch("radius", 4, targetPoint, new DistanceSorter(targetPoint), ignore);
    PriorityQueue<KDTreeNode> radiusIgnoreNaive =
        kdTree.naiveSearch("radius", 4, targetPoint, ignore);

    assertEquals(3, radiusIgnore.size());
    assertFalse(radiusIgnore.contains(targetPoint));
    assertArrayEquals(radiusIgnoreNaive.toArray(), radiusIgnore.toArray());
  }

  /** * Tests radius search without a point to ignore. */
  @Test
  public void testRadiusNoIgnore() {
    setUpOddPoints();

    Point targetPoint = new Point(new double[] {4, 1});

    PriorityQueue<KDTreeNode> radiusNoIgnore =
        kdTree.kdTreeSearch(
            "radius", 4, targetPoint, new DistanceSorter(targetPoint), new HashSet<>());
    PriorityQueue<KDTreeNode> radiusNoIgnoreNaive =
        kdTree.naiveSearch("radius", 4, targetPoint, new HashSet<>());

    assertEquals(4, radiusNoIgnore.size());
    assertTrue(radiusNoIgnore.contains(targetPoint));
    assertArrayEquals(radiusNoIgnoreNaive.toArray(), radiusNoIgnore.toArray());
  }
}
