package mvc;


import java.util.HashSet;
import java.util.Set;

public class Publisher {
    private final Set<Subscriber> subscribers = new HashSet<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        subscribers.forEach(Subscriber::update);
    }
}