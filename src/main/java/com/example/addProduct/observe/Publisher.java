package com.example.addProduct.observe;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Publisher {
    //field
    private final List<Subscriber> subscribers = new LinkedList<>() ;

    //method
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unSubscriber(Subscriber s) {
        subscribers.remove(s);

    }

    public void notifySubscribers() throws SQLException {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }
}
