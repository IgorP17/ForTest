import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class WireMockTestGet {

    // assume we have standalone wiremock running (config see in resources)

    private final static String baseURI = "http://igor-virtualbox:8080";
    private final static String getPath = "/test?myid=123";

    private final String path;
    private final int respCode;

    public WireMockTestGet(String path, int respCode) {
        this.path = path;
        this.respCode = respCode;
    }


    @Parameterized.Parameters(name = "{index}: path({0}), resp={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]
                {{getPath, 200},
                        {"/some_wrong_path", 404}
                });
    }

    @Test
    public void test() {
        System.out.printf("=== Running test with params path = %s, respCode = %d\n", path, respCode);
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(baseURI);
        request.header("X_HEADER", "QWE");

        System.out.println("=== Request is ===");
        request.log().all();/*.get(getPath).then()
                .assertThat().statusCode(200)
                .log().all();*/

        // Send request
        Response response = request.get(path);
        printResponse(response);

        Assert.assertEquals(respCode, response.getStatusCode());
    }

    /*
        Print response
     */
    private void printResponse(Response response) {
        System.out.println("\n=== Response is ===\n" +
                response.getHeaders() + "\n\n" +
                response.getStatusCode() + "\n\n" +
                response.asString());
    }
}
