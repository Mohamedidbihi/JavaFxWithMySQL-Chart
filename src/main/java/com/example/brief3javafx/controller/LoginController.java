package com.example.brief3javafx.controller;
import com.example.brief3javafx.Main;
import com.example.brief3javafx.dao.EmployeDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


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

    @FXML
    private void clickLogin(ActionEvent event) throws SQLException {
        EmployeDao cd = new EmployeDao();
        boolean emailValide;
        boolean passwordValide;
        if(isEmail(email.getText())){
            emailalert.setVisible(false);
            emailValide=true;
        }
        else{
            emailalert.setVisible(true);
            emailValide=false;

        }
         if(password.getText().trim().isEmpty()){
            passwordalert.setVisible(true);
             passwordValide=false;
        }else {
            passwordalert.setVisible(false);
             passwordValide=true;
        }
       // boolean res = new EmployeeImpl().logged(email.getText(),password.getText());
        if(passwordValide  && emailValide  ) {
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
                }
            } else {
                invalide.setVisible(true);
            }
        }
    }

    private boolean isEmail(String input){
        return  input.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]{2,3}+");
    }



}
