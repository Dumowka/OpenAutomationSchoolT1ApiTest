package api.tests.user;

import api.models.request.UserLoginRequest;
import api.models.request.UserRegistrationRequest;
import api.models.response.MessageDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import api.tests.CommonTest;

import static api.common.ApiClient.commonPost;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest extends CommonTest {
    @Test
    void postLoginTest() {
        commonPost(200, new UserLoginRequest("username", "password"), config.getConfigParameter("LOGIN_PATH"));
    }

    @Test
    void postRegisterTest() {
        var response = commonPost(
                201,
                new UserRegistrationRequest(
                        RandomStringUtils.randomAlphabetic(15),
                        RandomStringUtils.randomAlphabetic(15)
                ),
                config.getConfigParameter("REGISTER_PATH")
        ).extract().as(MessageDto.class);

        assertThat(response).usingRecursiveComparison().isEqualTo(new MessageDto("User registered successfully"));
    }
}
