import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

public class ChatRoom {
    String roomId; // Made public for User class reference.
    private Set<User> users;
    private static final Logger logger = Logger.getLogger(ChatRoom.class.getName());

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.users = new CopyOnWriteArraySet<>();
    }

    public ChatRoom joinRoom(User user) {
        users.add(user);
        logger.info(user.getName() + " joined room: " + roomId);
        notifyAllUsers(user.getName() + " joined the chat");
        return this;
    }

    public void leaveRoom(User user) {
        users.remove(user);
        logger.info(user.getName() + " left room: " + roomId);
        notifyAllUsers(user.getName() + " left the chat");
    }

    public void sendMessage(User sender, String message) {
        String formattedMessage = "[" + sender.getName() + "]: " + message;
        logger.info("Message sent in room " + roomId + ": " + formattedMessage);
        notifyAllUsers(formattedMessage);
    }

    private void notifyAllUsers(String message) {
        for (User user : users) {
            user.receiveMessage(message);
        }
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}

