
public class UserAuthentication {

    // ── Fields ────────────────────────────────────────────────────────────────

    /** The registered account used as the reference for authentication. */
    private UserAccount storedAccount;

    /** The username submitted during the login attempt. */
    private String submittedUsername;

    /** The password submitted during the login attempt. */
    private String submittedPassword;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * Constructs a UserAuthentication instance with the stored account
     * and the credentials submitted at login.
     *
     * @param storedAccount     The UserAccount object holding registered credentials.
     * @param submittedUsername The username entered during login.
     * @param submittedPassword The password entered during login.
     */
    public UserAuthentication(UserAccount storedAccount,
                              String submittedUsername,
                              String submittedPassword) {
        this.storedAccount     = storedAccount;
        this.submittedUsername = submittedUsername;
        this.submittedPassword = submittedPassword;
    }

    // ── Authentication ────────────────────────────────────────────────────────

    /**
     * Authenticates the user by comparing the submitted credentials
     * against the stored account details.
     *
     * Uses a decision structure to evaluate both fields simultaneously —
     * both the username and password must match for login to succeed.
     *
     * @return {@code true} if credentials match, {@code false} otherwise.
     */
    public boolean loginUser() {
        boolean correctUsername = storedAccount.getUsername().equals(submittedUsername);
        boolean correctPassword = storedAccount.getPassword().equals(submittedPassword);

        if (correctUsername && correctPassword) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Generates an appropriate status message based on the authentication outcome.
     *
     * On success, the username is split on the underscore to derive
     * a first name and last name for the welcome message.
     * Example: "ty_1" → first name "ty", identifier "1".
     *
     * @return A {@code String} confirming success or reporting failure.
     */
    public String returnLoginStatus() {
        if (loginUser()) {
            String[] nameComponents = submittedUsername.split("_");
            String firstName        = nameComponents.length > 0 ? nameComponents[0] : submittedUsername;
            String lastName         = nameComponents.length > 1 ? nameComponents[1] : "";

            return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
