import java.util.logging.Logger;

public class User {
    private String name;
    private ChatRoom currentRoom;
    private static final Logger logger = Logger.getLogger(User.class.getName());

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void joinRoom(String roomId) {
        currentRoom = ChatRoomManager.getInstance().createOrJoinRoom(roomId, this);
    }

    public void leaveRoom() {
        if (currentRoom != null) {
            ChatRoomManager.getInstance().removeUserFromRoom(currentRoom.roomId, this);
            currentRoom = null;
        }
    }

    public void sendMessage(String message) {
        if (currentRoom != null) {
            currentRoom.sendMessage(this, message);
        } else {
            logger.warning("User " + name + " is not in any chat room.");
        }
    }

    public void receiveMessage(String message) {
        System.out.println("[" + name + " received]: " + message);
    }
}
