package com.example.brief3javafx.dao;

import com.example.brief3javafx.dbConnexion.Connexion;
import com.example.brief3javafx.models.Client;
import com.example.brief3javafx.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeDao {

    public  static Employee auth;

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
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setFirstname(rs.getString(2));
                e.setLastname(rs.getString(3));
                e.setCin(rs.getString(4));
                e.setPhone(rs.getString(5));
                e.setEmail(rs.getString(6));
                e.setPassword(rs.getString(7));
                e.setEntity(rs.getString(8));
                auth = e;
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    return true;
    }

}
