package wiremock;

public class Settings {

    public final static String baseURI = "http://igor-virtualbox:8080";
    public final static String getPath = "/test";
    public final static String postPath = "/post";
    public final static String getPathParams = "?myid=123";
    public final static String postBody = "{\n" +
            "\t\"id\": THIS_ID,\n" +
            "\t\"name\": \"THIS_NAME\",\n" +
            "\t\"cost\": THIS_COST\n" +
            "}";
}
