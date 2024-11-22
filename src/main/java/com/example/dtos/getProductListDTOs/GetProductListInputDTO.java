package com.example.dtos.getProductListDTOs;

import com.example.interfaces.RequestData;

public class GetProductListInputDTO implements RequestData {
    private String type;

    public GetProductListInputDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
