package com.example.dtos.getProductListSevenDayExpiryDTOs;

import com.example.interfaces.RequestData;

public class GetProductListSevenDayExpiryInputDTO implements RequestData {
    private String type;

    public GetProductListSevenDayExpiryInputDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
