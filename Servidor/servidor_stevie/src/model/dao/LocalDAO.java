package model.dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDAO {
    public void create(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
