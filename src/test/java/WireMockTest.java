import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

public class WireMockTest {

    // assume we have standalone wiremock running (config see in resources)

    private final static String baseURI = "http://igor-virtualbox:8080";
    private final static String getPath = "/test?myid=123";


    @Test
    public void test1() {
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(baseURI);
        request.header("X_HEADER", "QWE");

        System.out.println("=== Request is ===");
        request.log().all();/*.get(getPath).then()
                .assertThat().statusCode(200)
                .log().all();*/

        // Send request
        Response response = request.get(getPath);
        printResponse(response);

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void test2(){
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(baseURI);
        request.header("X_HEADER", "QWE");

        System.out.println("=== Request is ===");
        request.log().all();

        // Send request
        Response response = request.get("/some_wrong_path");
        printResponse(response);

        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertTrue(response.asString().contains("Request was not matched"));
    }

    /*
        Print response
     */
    private void printResponse(Response response){
        System.out.println("\n=== Response is ===\n" +
                response.getHeaders() + "\n\n" +
                response.getStatusCode() + "\n\n" +
                response.asString());
    }
}
