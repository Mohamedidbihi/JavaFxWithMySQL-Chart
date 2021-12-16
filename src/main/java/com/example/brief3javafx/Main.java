package com.example.brief3javafx;

import com.example.brief3javafx.dbConnexion.Connexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 967, 568);
        stage.setTitle("Welcome  !");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Connexion.getConnection();
    }
    public static void main(String[] args) {
        launch();
    }
}