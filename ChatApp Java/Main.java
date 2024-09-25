import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Chat Application!");
        logger.info("Application started.");

        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            User user = new User(name);

            while (true) {
                System.out.println("\n1. Join a chat room\n2. Send a message\n3. Leave the chat room\n4. Exit");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter Chat Room ID: ");
                        String roomId = scanner.nextLine();
                        user.joinRoom(roomId);
                        break;
                    case "2":
                        System.out.print("Enter your message: ");
                        String message = scanner.nextLine();
                        user.sendMessage(message);
                        break;
                    case "3":
                        user.leaveRoom();
                        break;
                    case "4":
                        logger.info("Exiting the application.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
