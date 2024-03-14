package api.tests;

import api.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static api.common.ApiClient.getAccessToken;

public class CommonTest {
    protected static final ConfigProperties config = ConfigProperties.getInstance();

    protected static String accessToken;

    @BeforeAll
    static void beforeAllTestData() {
        RestAssured.replaceFiltersWith(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = config.getConfigParameter("BASE_URI");
        accessToken = getAccessToken().getAccess_token();
    }
}
