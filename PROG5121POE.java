import java.util.Scanner;

public class PROG5121POE {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        System.out.println("----------------------------------------------");
        System.out.println(" PROG5121 POE Registration & Login ");
        System.out.println("--------------------------------------------\n");

        System.out.println("---------- REGISTRATION ----------");

        String capturedUsername;

        while (true) {
            System.out.print("Enter username: ");
            capturedUsername = inputReader.nextLine();

            UserAccount usernameValidator =
                    new UserAccount(capturedUsername, "Placeholder1!", "+271234567");

            if (usernameValidator.checkUserName()) {
                System.out.println("Username successfully captured.\n");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that "
                        + "your username contains an underscore and is no more than five "
                        + "characters in length.\n");
            }
        }

        String capturedPassword;

        while (true) {
            System.out.print("Enter password: ");
            capturedPassword = inputReader.nextLine();

            UserAccount passwordValidator =
                    new UserAccount(capturedUsername, capturedPassword, "+271234567");

            if (passwordValidator.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.\n");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that "
                        + "the password contains at least eight characters, a capital letter, "
                        + "a number, and a special character.\n");
            }
        }

        String capturedContactNumber;

        while (true) {
            System.out.print("Enter cell phone number (e.g. +27789628127): ");
            capturedContactNumber = inputReader.nextLine();

            UserAccount contactValidator =
                    new UserAccount(capturedUsername, capturedPassword, capturedContactNumber);

            if (contactValidator.checkCellPhoneNumber()) {
                System.out.println("Cell number successfully captured.\n");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain "
                        + "an international code; please correct the number and try again.\n");
            }
        }

        System.out.println("Registration successful!\n");

        System.out.println("---------- LOGIN ----------");

        UserAccount registeredAccount =
                new UserAccount(capturedUsername, capturedPassword, capturedContactNumber);

        System.out.print("Enter username: ");
        String loginUsername = inputReader.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = inputReader.nextLine();

        UserAuthentication authSession =
                new UserAuthentication(registeredAccount, loginUsername, loginPassword);

        System.out.println("\n" + authSession.returnLoginStatus());

        if (!authSession.loginUser()) {
            System.out.println("Login failed. You cannot access QuickChat.");
            inputReader.close();
            return;
        }

        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;

        while (running) {

            System.out.println("\nSelect an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            int choice = inputReader.nextInt();
            inputReader.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("How many messages would you like to send? ");
                    int numberOfMessages = inputReader.nextInt();
                    inputReader.nextLine();

                    for (int i = 1; i <= numberOfMessages; i++) {

                        System.out.println("\nMessage " + i);

                        String recipient;

                        while (true) {
                            System.out.print("Enter recipient number: ");
                            recipient = inputReader.nextLine();

                            if (recipient.startsWith("+") && recipient.length() <= 13) {
                                System.out.println("Cell phone number successfully captured.");
                                break;
                            }

                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                        }

                        String messageText;

                        while (true) {
                            System.out.print("Enter your message: ");
                            messageText = inputReader.nextLine();

                            if (messageText.length() <= 250) {
                                System.out.println("Message ready to send.");
                                break;
                            }

                            int excessCharacters = messageText.length() - 250;

                            System.out.println("Message exceeds 250 characters by "
                                    + excessCharacters
                                    + ", please reduce size.");
                        }

                        Message message =
                                new Message(i, recipient, messageText);

                        System.out.println("\nChoose message action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");

                        int action = inputReader.nextInt();
                        inputReader.nextLine();

                        System.out.println(message.sentMessage(action));

                        if (action == 1) {
                            System.out.println("\n" + message.printMessages());
                        }
                    }

                    System.out.println("\nTotal messages sent: "
                            + Message.returnTotalMessages());

                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    running = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option selected.");
            }
        }

        inputReader.close();
    }
}