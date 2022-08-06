import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class WireMockGetTest {

    // assume we have standalone wiremock running (config see in resources)

    private final String path;
    private final int respCode;
    private final String bodyPart;

    public WireMockGetTest(String path, int respCode, String bodyPart) {
        this.path = path;
        this.respCode = respCode;
        this.bodyPart = bodyPart;
    }


    @Parameterized.Parameters(name = "{index}: path({0}), resp={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]
                {
                        {Settings.getPath, 200, "Base url"},
                        {Settings.getPath + Settings.getPathParams, 200, "БУЙА"},
                        {"/some_wrong_path", 404, "Request was not matched"}
                });
    }

    @Test
    public void test() {
        System.out.printf("=== Running test with params path = %s, respCode = %d\n", path, respCode);
        RequestSpecification request = RestAssured.given();
        // Setting Request URL
        request.baseUri(Settings.baseURI);
        request.header("X_HEADER", "QWE");

        System.out.println("=== Request is ===");
        request.log().all();/*.get(getPath).then()
                .assertThat().statusCode(200)
                .log().all();*/

        // Send request
        Response response = request.get(path);
        printResponse(response);

        ArrayList<CompareItem> compareItems = new ArrayList<>();
        compareItems.add(new CompareItem(String.valueOf(response.getStatusCode()), String.valueOf(respCode), CompareEnum.EQUALS, "Status code"));
        compareItems.add(new CompareItem(response.getBody().asString(), bodyPart, CompareEnum.CONTAINS, "Body"));

        boolean result = CompareItems.compareItems(compareItems);

        Assert.assertTrue("Checkings", result);
    }

    /*
        Print response
     */
    private void printResponse(Response response) {
        System.out.println("\n=== Response is ===\n" +
                response.getHeaders() + "\n\n" +
                response.getStatusCode() + "\n\n" +
                response.asString());
        System.out.println("=== END Response ===");
    }
}
