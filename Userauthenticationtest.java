import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserAuthenticationTest {

    // ── Test fixture ──────────────────────────────────────────────────────────

    /** Shared registered account used across all login tests. */
    private UserAccount testAccount;

    /**
     * Initialises a valid registered account before each test.
     */
    @BeforeEach
    public void initialiseTestAccount() {
        testAccount = new UserAccount("ty_1", "Secure@99!", "+27789628127");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // loginUser()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: Login successful.
     * Condition: Correct username and password submitted.
     * Expected result: true.
     */
    @Test
    @DisplayName("loginUser returns true when correct credentials are submitted")
    public void testLoginWithCorrectCredentialsReturnsTrue() {
        UserAuthentication session = new UserAuthentication(testAccount, "ty_1", "Secure@99!");
        assertTrue(session.loginUser(),
                "Expected TRUE: submitted credentials match the registered account.");
    }

    /**
     * Test: Login failed — wrong password.
     * Condition: Correct username but incorrect password submitted.
     * Expected result: false.
     */
    @Test
    @DisplayName("loginUser returns false when incorrect password is submitted")
    public void testLoginWithWrongPasswordReturnsFalse() {
        UserAuthentication session = new UserAuthentication(testAccount, "ty_1", "wrongpass1!");
        assertFalse(session.loginUser(),
                "Expected FALSE: submitted password does not match the registered password.");
    }

    /**
     * Test: Login failed — wrong username.
     * Condition: Incorrect username submitted.
     * Expected result: false.
     */
    @Test
    @DisplayName("loginUser returns false when incorrect username is submitted")
    public void testLoginWithWrongUsernameReturnsFalse() {
        UserAuthentication session = new UserAuthentication(testAccount, "ab_1", "Secure@99!");
        assertFalse(session.loginUser(),
                "Expected FALSE: submitted username does not match the registered username.");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // returnLoginStatus()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: Successful login returns welcome message.
     * Expected: Message contains "welcome" and "great to see you".
     */
    @Test
    @DisplayName("returnLoginStatus returns welcome message on successful login")
    public void testSuccessfulLoginReturnsWelcomeMessage() {
        UserAuthentication session = new UserAuthentication(testAccount, "ty_1", "Secure@99!");
        String statusMessage = session.returnLoginStatus();
        assertTrue(statusMessage.toLowerCase().contains("welcome"),
                "Expected 'welcome' in the successful login status message.");
        assertTrue(statusMessage.toLowerCase().contains("great to see you"),
                "Expected 'great to see you' in the successful login status message.");
    }

    /**
     * Test: Failed login returns error message.
     * Expected: Message contains "incorrect".
     */
    @Test
    @DisplayName("returnLoginStatus returns error message on failed login")
    public void testFailedLoginReturnsErrorMessage() {
        UserAuthentication session = new UserAuthentication(testAccount, "ty_1", "wrongpass1!");
        String statusMessage = session.returnLoginStatus();
        assertTrue(statusMessage.toLowerCase().contains("incorrect"),
                "Expected 'incorrect' in the failed login status message.");
    }
}