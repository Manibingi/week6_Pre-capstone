package com.shopkart.data.db;

public final class SqlQueries {

    private SqlQueries() {}

    public static final String CART_TOTAL = """
            SELECT COALESCE(SUM(qty * unit_price_paise),0)
            FROM cart_items
            WHERE cart_id = ?
            """;

    public static final String ORDER_STATUS = """
            SELECT status
            FROM orders
            WHERE id = ?
            """;

    public static final String ORDER_TOTAL = """
            SELECT total_paise
            FROM orders
            WHERE id = ?
            """;

    public static final String CUSTOMER_ID = """
            SELECT id
            FROM customers
            WHERE persona = ?
            """;

    public static final String PLACED_ORDER_COUNT = """
            SELECT COUNT(*)
            FROM orders
            WHERE customer_id = ?
            AND status='PLACED'
            """;

}