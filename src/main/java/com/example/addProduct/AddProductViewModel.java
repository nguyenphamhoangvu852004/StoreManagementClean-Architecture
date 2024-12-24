package com.example.addProduct;

import com.example.addProduct.observe.Publisher;

import java.sql.SQLException;

public class AddProductViewModel extends Publisher {
    private String message;

    public AddProductViewModel(String message) {
        this.message = message;
    }

    public AddProductViewModel( ) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws SQLException {
        this.message = message;
        notifySubscribers();
    }
}
