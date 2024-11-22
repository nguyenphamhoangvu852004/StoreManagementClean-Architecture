package com.example.addProduct;

import com.example.addProduct.observe.Publisher;

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

    public void setMessage(String message) {
        this.message = message;
        notifySubscribers();
    }
}
