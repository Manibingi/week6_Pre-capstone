package com.shopkart.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepository extends DbSupport {

    public int totalPaise(long cartId) {

        try (
                Connection connection = connection();
                PreparedStatement statement = connection.prepareStatement(SqlQueries.CART_TOTAL)
        ) {

            statement.setLong(1, cartId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}