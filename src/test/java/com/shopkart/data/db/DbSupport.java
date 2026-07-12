package com.shopkart.data.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbSupport {

    protected Connection connection() throws SQLException {
        return DbConfig.connection();
    }

}