package com.example.brief3javafx.controller;

import com.example.brief3javafx.Main;
import com.example.brief3javafx.dao.EmployeDao;
import com.example.brief3javafx.enums.enumRegex;
import com.example.brief3javafx.helpers.Regex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Label emailalert;

    @FXML
    private Label passwordalert;

    @FXML
    private Pane invalide;

   final static Logger log = Logger.getLogger(LoginController.class.getName());

    @FXML
    private void clickLogin(ActionEvent event) throws SQLException {
        EmployeDao cd = new EmployeDao();
        boolean emailRegex = Regex.matchesRegex(email.getText(), enumRegex.EMAIL.getPattern());
        boolean passwordValide;
        if(emailRegex){
            emailalert.setVisible(false);
        }
        else{
            emailalert.setVisible(true);
        }
         if(password.getText().trim().isEmpty()){
            passwordalert.setVisible(true);
             passwordValide=false;
        }else {
            passwordalert.setVisible(false);
             passwordValide=true;
        }
       // boolean res = new EmployeeImpl().logged(email.getText(),password.getText());
        if(passwordValide  && emailRegex  ) {
            boolean res = cd.login(email.getText(), password.getText());
            if (res) {
                try {

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newClient.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 967, 568);
                    stage.setTitle("Add Clients !");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("erreur de passer a autre interface");
                }
            } else {
                invalide.setVisible(true);
                CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
                    invalide.setVisible(false);
                });
                log.warn("Les donneés sont incorrect auth "+email.getText()+"");
            }
        }
    }
}
