package com.example.removeProduct.dto;

public class RemoveProductOutputDTO {
    private String message;

    public RemoveProductOutputDTO(String message) {
        this.message = message;
    }

    public RemoveProductOutputDTO( ) {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
