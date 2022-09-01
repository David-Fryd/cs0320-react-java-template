# README

## S T A R S

### Known Bugs
No known bugs!

### Design Details
#### k-d Tree
My k-d Tree is implemented within the KDTree class; it is initialized with a type (T extends KDTreeNode) and its
attributes include the head, two KDTrees as the left and right subtrees, the current relevant axis, and an
ArrayList of all of the nodes in the current tree.

To construct the k-d tree, I opted to use a Comparator (the AxisSorter class) to sort the list of nodes by the
relevant axis. That way, if someone were to want to modify the way that my KDTree initializes, they could write
their own comparator to do so.

The nodes of a KDTree are represented as any class that implements the KDTreeNode Interface. To implement this
interface, a class must supply three methods: getPoint(), getDimension(), and euclideanDistance(KDTreeNode node).
The thought process behind including these methods and using an Interface to outline valid nodes for a KDTree is
because it does not really matter what other data that the nodes contain; as long as one can find the current 
point for a given node and find the euclidean distance between that node and another node, a k-d tree can be
successfully constructed. For instance, within the Stars package of this project, I included both a barebones
'Point' class that just stored data for a k-dimensional point as well as a 'Star' class that also possessed 
attributes for an ID and a name. 

The k-nearest-neighbors search and the radius search both share the same method (kdTreeSearch), as their algorithms 
only differ in a few small places, which is handled by specifying which search is desired when calling the method.
This makes the code less repetitive and allows for the potential of extensibility if someone wanted to
implement a new k-d tree search algorithm that uses a similar traversal method but differs in its search criteria.
Additionally, both algorithms use a PriorityQueue that is sorted by a Comparator (the DistanceSorter class). This makes
the code ready for change, as if someone wanted to change the criteria by which the KDTreeNodes were ordered and 
compared, they could do so by writing their own comparator. I also wrote a naive implementation of the 
k-nearest-neighbors and radius search algorithms strictly for testing purposes; if I were to expand upon this project 
in the future, I would like to write an oracle that automates testing by randomly generating input stars and compares 
the results of the naive implementations and the written implementations of nearest neighbors and radius search to 
add thoroughness.

#### REPL
To design an extensible REPL, I divided the problem into three parts. At the lowest level is the Command Interface,
which has a method named execute that takes arguments (List<String>).

Commands that are associated together are all included within a class that extends the Program Interface, which runs 
a specific command with an "execute" method that takes a command name and arguments. A class that extends the 
Program Interface would also need to store a HashMap<String, Command> that maps command names to specific instances 
of the commands that the program includes. This additional "program" layer between input and command execution serves to allow related 
commands to share data. 

To handle user input, I defined a REPL class which reads input from the command line, parses it via regex, and then
feeds it into the current program, which is defined upon construction of a REPL.

### Optimizations
No optimizations were made over the implementation of k-d tree construction, k-nearest-neighbors search, or
radius search; the latter two operations should be logarithmic, assuming the k-d tree construction results in a
balanced tree.

### How to Run Tests
For JUnit tests: \
`mvn test`

### How to Build and Run
To build: \
`mvn package`

To run: \
`./run` will start a command line REPL. \

### Design Questions
- Suppose that in addition to "neighbors" and "radius" 
you wanted to support 10+ more commands. How would you change your code - 
particularly your repl parsing - to do this? Don't worry about algorithmic 
details with the k-d tree, we're interested in the design.


This project's REPL parsing is described in good detail above under the "Design Details" section. With that in mind, to
add additional commands to my REPL, I would need to add those commands to a HashMap within a class that implements the 
Program Interface, and then write new classes for each new command that executes the command given specific arguments.


- What are some problems you would run into if you wanted to use your k-d tree 
to find points that are close to each other on the earth's surface? You do not 
need to explain how to solve these problems.


Since the current implementation of the k-nearest-neighbors algorithm only considers the straight-line
distance between two points, the tree would not be able to accurately determine if points are close on the Earth's 
surface because the Earth's surface is curved (sorry flat-earthers!). For instance, according to the current algorithm,
the distance between my location on Earth now and the point exactly opposite to my location on Earth's surface would 
be equal to the diameter of the Earth; in reality, the surface distance is approximately half of the Earth's 
circumference. If the algorithm were to try to consider that the Earth is curved, it may also have issues due to varying
altitudes around the Earth's surface, so the curvature would be variable and calculating distance would be difficult.


- Your k-d tree supports most of the methods required by the Collection interface.
What would you have to add/change to make code like 
`Collection<Star> db = new KDTree<Star>()` compile and work properly?

To make my k-d tree implement the Collection interface, my KDTree class would have to implement a variety of new 
methods that are required by the Collection interface. size() would recursively count the number of nodes in the tree; 
isEmpty() would return true if the tree's head was null and had no children; contains() would search for a given node
and return if the tree contained the node; iterator() would return an iterator over the nodes in the tree; toArray() 
would return an array of all nodes in the tree (which is already nearly implemented, since one of the attributes of a 
KDTree is an ArrayList of its nodes); add() would add a given node to the tree; remove() would remove a given node from
the tree, if present; containsAll() would tell if the tree contains all nodes given in the input collection; addAll() 
would add all elements to the tree present in the given collection; removeAll() would remove all elements from the tree 
that are contained in the given collection, if present; retainAll() would retain only the elements in the tree that are 
present in the given collection; clear() would remove all nodes from the tree. If all of these methods were implemented,
then the KDTree would implement the Collection Interface and the code would compile and work properly.
