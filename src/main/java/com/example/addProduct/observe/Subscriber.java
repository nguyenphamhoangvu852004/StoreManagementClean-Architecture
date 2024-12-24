package com.example.addProduct.observe;

import java.sql.SQLException;

public interface Subscriber {

    public void update() throws SQLException;

}