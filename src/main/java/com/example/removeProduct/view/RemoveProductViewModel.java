package com.example.removeProduct.view;

import com.example.addProduct.observe.Publisher;

import java.sql.SQLException;

public class RemoveProductViewModel extends Publisher {
    private String message;

    public RemoveProductViewModel(String message) {
        this.message = message;
    }

    public RemoveProductViewModel() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws SQLException {
        this.message = message;
        notifySubscribers();
    }
}
