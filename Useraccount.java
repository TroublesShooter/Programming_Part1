
public class UserAccount {

    // ── Fields ────────────────────────────────────────────────────────────────

    /** Unique identifier chosen by the user during account creation. */
    private String accountUsername;

    /** Secret key chosen by the user; must meet complexity standards. */
    private String accountPassword;

    /** User's contact number including the international dialling code. */
    private String contactNumber;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * Creates a new UserAccount with the provided registration details.
     *
     * @param accountUsername The username entered by the user.
     * @param accountPassword The password entered by the user.
     * @param contactNumber   The cell phone number entered by the user.
     */
    public UserAccount(String accountUsername, String accountPassword, String contactNumber) {
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.contactNumber   = contactNumber;
    }

    // ── Validation ────────────────────────────────────────────────────────────

    /**
     * Verifies that the username meets the required format:
     * <ul>
     *   <li>Must contain an underscore character (_).</li>
     *   <li>Must be no longer than five characters.</li>
     * </ul>
     *
     * @return {@code true} if the username is valid, {@code false} otherwise.
     */
    public boolean checkUserName() {
        if (accountUsername == null) {
            return false;
        }
        boolean hasUnderscore  = accountUsername.contains("_");
        boolean withinMaxLimit = accountUsername.length() <= 5;
        return hasUnderscore && withinMaxLimit;
    }

    /**
     * Verifies that the password satisfies all complexity requirements:
     * <ul>
     *   <li>Minimum length of eight characters.</li>
     *   <li>At least one uppercase letter.</li>
     *   <li>At least one numeric digit.</li>
     *   <li>At least one non-alphanumeric (special) character.</li>
     * </ul>
     *
     * @return {@code true} if the password is complex enough, {@code false} otherwise.
     */
    public boolean checkPasswordComplexity() {
        if (accountPassword == null || accountPassword.length() < 8) {
            return false;
        }

        boolean upperCaseFound  = false;
        boolean numberFound     = false;
        boolean specialFound    = false;

        for (char ch : accountPassword.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCaseFound = true;
            } else if (Character.isDigit(ch)) {
                numberFound = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                specialFound = true;
            }
        }

        return upperCaseFound && numberFound && specialFound;
    }

    /**
     * Verifies that the contact number is in a valid international format
     * using a regular expression.
     *
     * Requirements:
     * <ul>
     *   <li>Must begin with a '+' symbol representing the international prefix.</li>
     *   <li>Must be followed by between 7 and 12 digits.</li>
     * </ul>
     *
     * Regex pattern applied: {@code ^\+[0-9]{7,12}$}
     * <ul>
     *   <li>{@code ^}         – start of input</li>
     *   <li>{@code \+}        – literal plus sign (international prefix)</li>
     *   <li>{@code [0-9]{7,12}} – between 7 and 12 numeric digits</li>
     *   <li>{@code $}         – end of input</li>
     * </ul>
     *
     * Example: +27789628127 is valid (country code +27, 9-digit number).
     *
     * Regex reference:
     * Goyvaerts, J., and Levithan, S. (2012). Regular Expressions Cookbook
     * (2nd ed.). O'Reilly Media.
     * https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/
     *
     * @return {@code true} if the contact number is valid, {@code false} otherwise.
     */
    public boolean checkCellPhoneNumber() {
        if (contactNumber == null) {
            return false;
        }
        // Pattern: '+' followed by 7 to 12 digits covering SA and other international formats
        String internationalNumberPattern = "^\\+[0-9]{7,12}$";
        return contactNumber.matches(internationalNumberPattern);
    }

    /**
     * Attempts to register the account by running all three validations.
     * Validation runs in order: username → password → contact number.
     * The first failure produces an appropriate error message.
     *
     * @return A {@code String} describing the registration outcome.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username "
                    + "contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password "
                    + "contains at least eight characters, a capital letter, a number, "
                    + "and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international "
                    + "code; please correct the number and try again.";
        }

        return "Registration successful!\n"
                + "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** @return The account username. */
    public String getUsername()      { return accountUsername; }

    /** @return The account password. */
    public String getPassword()      { return accountPassword; }

    /** @return The account contact number. */
    public String getContactNumber() { return contactNumber; }
}