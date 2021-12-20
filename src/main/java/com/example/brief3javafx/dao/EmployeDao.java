package com.example.brief3javafx.dao;

import com.example.brief3javafx.dbConnexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeDao {

    public boolean login(String email, String password) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query_POST = "SELECT * FROM employee WHERE email = ? and password = ?";
            statement = connection.prepareStatement(query_POST);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    return true;
    }

}
