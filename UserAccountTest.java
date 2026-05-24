import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserAccountTest {

    // ─────────────────────────────────────────────────────────────────────────
    // checkUserName()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: Username correctly formatted.
     * Input: "ty_1" — contains underscore, length = 4.
     * Expected result: true.
     */
    @Test
    @DisplayName("checkUserName returns true for valid username ty_1")
    public void testValidUsernameReturnsTrue() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "+27789628127");
        assertTrue(account.checkUserName(),
                "Expected TRUE: 'ty_1' contains underscore and is within 5 characters.");
    }

    /**
     * Test: Username incorrectly formatted.
     * Input: "tiyani123" — no underscore and exceeds 5 characters.
     * Expected result: false.
     */
    @Test
    @DisplayName("checkUserName returns false for invalid username tiyani123")
    public void testInvalidUsernameReturnsFalse() {
        UserAccount account = new UserAccount("tiyani123", "Secure@99!", "+27789628127");
        assertFalse(account.checkUserName(),
                "Expected FALSE: 'tiyani123' has no underscore and exceeds 5 characters.");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // checkPasswordComplexity()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: Password meets complexity requirements.
     * Input: "Secure@99!" — has uppercase, digit, special character, length > 8.
     * Expected result: true.
     */
    @Test
    @DisplayName("checkPasswordComplexity returns true for valid password Secure@99!")
    public void testValidPasswordReturnsTrue() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "+27789628127");
        assertTrue(account.checkPasswordComplexity(),
                "Expected TRUE: 'Secure@99!' meets all password complexity requirements.");
    }

    /**
     * Test: Password does not meet complexity requirements.
     * Input: "simple" — too short, no uppercase, digit, or special character.
     * Expected result: false.
     */
    @Test
    @DisplayName("checkPasswordComplexity returns false for invalid password simple")
    public void testInvalidPasswordReturnsFalse() {
        UserAccount account = new UserAccount("ty_1", "simple", "+27789628127");
        assertFalse(account.checkPasswordComplexity(),
                "Expected FALSE: 'simple' does not meet password complexity requirements.");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // checkCellPhoneNumber()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: Cell phone number correctly formatted.
     * Input: "+27789628127" — starts with '+', valid digit count.
     * Expected result: true.
     */
    @Test
    @DisplayName("checkCellPhoneNumber returns true for valid number +27789628127")
    public void testValidContactNumberReturnsTrue() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "+27789628127");
        assertTrue(account.checkCellPhoneNumber(),
                "Expected TRUE: '+27789628127' is a valid international number.");
    }

    /**
     * Test: Cell phone number incorrectly formatted.
     * Input: "0789628127" — missing international code prefix.
     * Expected result: false.
     */
    @Test
    @DisplayName("checkCellPhoneNumber returns false for number without international code")
    public void testInvalidContactNumberReturnsFalse() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "0789628127");
        assertFalse(account.checkCellPhoneNumber(),
                "Expected FALSE: '0789628127' does not contain an international code.");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // registerUser()
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Test: All fields valid — registration succeeds.
     * Expected: Message contains "successful".
     */
    @Test
    @DisplayName("registerUser returns success message when all inputs are valid")
    public void testRegisterUserSuccessfully() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "+27789628127");
        String outcome = account.registerUser();
        assertTrue(outcome.toLowerCase().contains("successful"),
                "Expected success message when all registration inputs are valid.");
    }

    /**
     * Test: Invalid username — registration fails with username error.
     */
    @Test
    @DisplayName("registerUser returns username error for invalid username")
    public void testRegisterUserUsernameError() {
        UserAccount account = new UserAccount("tiyani123", "Secure@99!", "+27789628127");
        String outcome = account.registerUser();
        assertTrue(outcome.toLowerCase().contains("username"),
                "Expected error message referencing 'username'.");
    }

    /**
     * Test: Invalid password — registration fails with password error.
     */
    @Test
    @DisplayName("registerUser returns password error for invalid password")
    public void testRegisterUserPasswordError() {
        UserAccount account = new UserAccount("ty_1", "simple", "+27789628127");
        String outcome = account.registerUser();
        assertTrue(outcome.toLowerCase().contains("password"),
                "Expected error message referencing 'password'.");
    }

    /**
     * Test: Invalid contact number — registration fails with cell number error.
     */
    @Test
    @DisplayName("registerUser returns cell number error for invalid contact number")
    public void testRegisterUserContactNumberError() {
        UserAccount account = new UserAccount("ty_1", "Secure@99!", "0789628127");
        String outcome = account.registerUser();
        assertTrue(outcome.toLowerCase().contains("cell"),
                "Expected error message referencing 'cell'.");
    }
}
