import org.example.GithubAPI;
import org.example.GithubUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GithubAPITest {
    private GithubAPI githubAPI;

    @BeforeEach
    public void setup() {
        String token = "GITHUB_TOKEN";
        githubAPI = new GithubAPI(token);
    }

    @Test
    public void testGetUser_ValidUsername_ReturnsGithubUser() {
        String validUsername = "sezko23";
        try {
            GithubUser githubUser = githubAPI.getUser(validUsername);
            Assertions.assertNotNull(githubUser);
            Assertions.assertEquals(validUsername, githubUser.getLogin());
        } catch (Exception e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetUser_InvalidUsername_ThrowsException() {
        String invalidUsername = "non-existing-username";
        Assertions.assertThrows(Exception.class, () -> {
            githubAPI.getUser(invalidUsername);
        });
    }

    @Test
    public void testGetUser_InvalidToken_ThrowsException() {
        String invalidToken = "invalid-github-token";
        GithubAPI githubAPI = new GithubAPI(invalidToken);
        String validUsername = "sezko23";
        Assertions.assertThrows(Exception.class, () -> {
            githubAPI.getUser(validUsername);
        });
    }
}