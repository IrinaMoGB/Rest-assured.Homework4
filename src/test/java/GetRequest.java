
import lesson4.GetResponse;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetRequest extends AbstractTest {
    @Test
    void websiteResponse()
    {
        given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void responseValues()
    {
        GetResponse responseInfo =
                given().spec(getRequestSpecification())
                        .when()
                        .get(getBaseUrl() + "recipes/complexSearch").prettyPeek()
                        .then()
                        .spec(getResponseSpecification())
                        .extract()
                        .body()
                        .as(GetResponse.class);

        assertThat(responseInfo.getOffset(), equalTo(0));
        assertThat(responseInfo.getNumber(), equalTo(10));
        assertThat(responseInfo.getTotalResults(), equalTo(5222));
    }


}
