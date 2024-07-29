package org.ImtiazSuperMarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

    static final String DB_URL = "jdbc:mysql://localhost:3306/imtias_super_marketdb";
    static final String USER = "root";
    static final String PASS = "root";
    Connection connection;
    BaseDao(){
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
