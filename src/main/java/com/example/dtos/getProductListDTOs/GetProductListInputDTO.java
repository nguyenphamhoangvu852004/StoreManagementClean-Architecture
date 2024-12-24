package com.example.dtos.getProductListDTOs;

import com.example.interfaces.RequestData;

public class GetProductListInputDTO implements RequestData {
    private String type;
    private int currentPage;
    private int pageSize;

    public GetProductListInputDTO(String type, int currentPage, int pageSize) {
        this.type = type;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
