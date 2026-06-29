import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @After
    public void tearDown() {
        userService.clearUsers();
    }

    @Test
    public void testAddUserCountIncreases() {
        String newUser = "Alice";

        userService.addUser(newUser);

        int count = userService.getUserCount();
        assertEquals(1, count);
    }
}