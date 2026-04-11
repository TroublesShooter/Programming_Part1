import java.util.Scanner;

public class PROG5121POE {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        System.out.println("----------------------------------------------");
        System.out.println("   PROG5121 POE Registration & Login     ");
        System.out.println("--------------------------------------------\n");

        // ── REGISTRATION ─────────────────────────────────────────────────────
        System.out.println("---------- REGISTRATION ----------");

        // Capture and validate username
        String capturedUsername;
        while (true) {
            System.out.print("Enter username: ");
            capturedUsername = inputReader.nextLine();

            UserAccount usernameValidator = new UserAccount(capturedUsername, "Placeholder1!", "+271234567");

            if (usernameValidator.checkUserName()) {
                System.out.println("Username successfully captured.\n");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that "
                        + "your username contains an underscore and is no more than five "
                        + "characters in length.\n");
            }
        }

        // Capture and validate password
        String capturedPassword;
        while (true) {
            System.out.print("Enter password: ");
            capturedPassword = inputReader.nextLine();

            UserAccount passwordValidator = new UserAccount(capturedUsername, capturedPassword, "+271234567");

            if (passwordValidator.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.\n");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that "
                        + "the password contains at least eight characters, a capital letter, "
                        + "a number, and a special character.\n");
            }
        }

        // Capture and validate contact number
        String capturedContactNumber;
        while (true) {
            System.out.print("Enter cell phone number (e.g. +27789628127): ");
            capturedContactNumber = inputReader.nextLine();

            UserAccount contactValidator = new UserAccount(capturedUsername, capturedPassword, capturedContactNumber);

            if (contactValidator.checkCellPhoneNumber()) {
                System.out.println("Cell number successfully captured.\n");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain "
                        + "an international code; please correct the number and try again.\n");
            }
        }

        System.out.println("Registration successful!\n");

        // ── LOGIN ─────────────────────────────────────────────────────────────
        System.out.println("---------- LOGIN ----------");

        UserAccount registeredAccount = new UserAccount(capturedUsername, capturedPassword, capturedContactNumber);

        System.out.print("Enter username: ");
        String loginUsername = inputReader.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = inputReader.nextLine();

        UserAuthentication authSession = new UserAuthentication(registeredAccount, loginUsername, loginPassword);

        System.out.println("\n" + authSession.returnLoginStatus());

        inputReader.close();
    }
}