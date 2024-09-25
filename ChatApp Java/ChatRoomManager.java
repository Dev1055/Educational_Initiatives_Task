import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ChatRoomManager {
    private static ChatRoomManager instance;
    private ConcurrentHashMap<String, ChatRoom> chatRooms;
    private static final Logger logger = Logger.getLogger(ChatRoomManager.class.getName());

    private ChatRoomManager() {
        chatRooms = new ConcurrentHashMap<>();
    }

    public static synchronized ChatRoomManager getInstance() {
        if (instance == null) {
            instance = new ChatRoomManager();
        }
        return instance;
    }

    public ChatRoom createOrJoinRoom(String roomId, User user) {
        return chatRooms.computeIfAbsent(roomId, id -> {
            logger.info("Creating new chat room with ID: " + roomId);
            return new ChatRoom(roomId);
        }).joinRoom(user);
    }

    public void removeUserFromRoom(String roomId, User user) {
        ChatRoom room = chatRooms.get(roomId);
        if (room != null) {
            room.leaveRoom(user);
            if (room.isEmpty()) {
                chatRooms.remove(roomId);
                logger.info("Chat room " + roomId + " is empty and removed.");
            }
        }
    }
}
