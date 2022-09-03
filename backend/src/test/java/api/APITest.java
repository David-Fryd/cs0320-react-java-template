package api;

public class APITest {
  // private static JsonAdapter<List<List<String>>> adapter;

  // @BeforeClass
  // public static void setupServer() {
  //   Spark.port(1234);
  //   Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
  // }

  // @Before
  // public void setupEndpoints() {
  //   ServerState server = new ServerState();
  //   Spark.get("/loadcsv", new LoadCSVHandler(server));
  //   Spark.get("/getcsv", new GetCSVHandler(server));
  // }

  // @BeforeClass
  // public static void setupMoshi() {
  //   Moshi mosh = new Moshi.Builder().build();
  //   Type listStringString =
  //       Types.newParameterizedType(
  //           List.class, Types.newParameterizedType(List.class, String.class));
  //   adapter = mosh.adapter(listStringString);
  // }

  // @Test
  // public void testLoad() throws IOException, InterruptedException {
  //   String reqUri = "http://localhost:1234/loadcsv?file=dummy.csv";
  //   HttpRequest request = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
  //   HttpResponse<String> response =
  //       HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
  //   assertEquals(200, response.statusCode());
  //   assertEquals("Loaded data/dummy.csv with 4 rows, 3 columns", response.body());

  //   // check that state has changed?
  // }

  // @Test
  // public void testBadFile() throws IOException, InterruptedException {
  //   String reqUri = "http://localhost:1234/loadcsv?file=../secretfile.csv";
  //   HttpRequest request = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
  //   HttpResponse<String> response =
  //       HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
  //   assertEquals(response.statusCode(), 400);
  // }

  // @Test
  // public void testGet() throws IOException, InterruptedException {
  //   String loadUri = "http://localhost:1234/loadcsv?file=dummy.csv";
  //   String getUri = "http://localhost:1234/getcsv";
  //   HttpRequest request = HttpRequest.newBuilder().uri(URI.create(loadUri)).build();
  //   HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
  //   HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create(getUri)).build();
  //   HttpResponse<String> response =
  //       HttpClient.newBuilder().build().send(request2, HttpResponse.BodyHandlers.ofString());
  //   assertEquals(200, response.statusCode());
  //   List<List<String>> parsed = adapter.fromJson(response.body());
  //   // create expected
  //   List<List<String>> expected = new ArrayList<>();
  //   expected.add(Arrays.asList("hi", "hi", "hi"));
  //   expected.add(Arrays.asList("fr", "fr", "fr"));
  //   expected.add(Arrays.asList("lol", "lol", "lol"));
  //   expected.add(Arrays.asList("dn", "dn", "dn"));
  //   assertEquals(expected, parsed);
  // }
}
