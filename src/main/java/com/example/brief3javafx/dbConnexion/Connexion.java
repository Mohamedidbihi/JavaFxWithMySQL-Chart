package com.example.brief3javafx.dbConnexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance;

    private Connexion() {
    }


    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mutuellecentr", "root", "admin");
        } catch (ClassNotFoundException exception) {
            System.out.println("Connection to database has a problem :( " + exception.getMessage());
        }
        return connection;
    }


}
