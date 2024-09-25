import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String event);
}

// Concrete Observer
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println("Hey " + name + ", new event: " + event);
    }
}

// Subject class
class EventNotifier {
    private List<Observer> observers = new ArrayList<>();
    private String event;

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void setEvent(String event) {
        this.event = event;
        notifyAllObservers();
    }
}

// Test Observer Pattern
public class ObserverPatternDemo {
    public static void main(String[] args) {
        EventNotifier notifier = new EventNotifier();

        Observer user1 = new User("Alice");
        Observer user2 = new User("Bob");

        notifier.subscribe(user1);
        notifier.subscribe(user2);

        notifier.setEvent("Rain is expected tomorrow");
    }
}
