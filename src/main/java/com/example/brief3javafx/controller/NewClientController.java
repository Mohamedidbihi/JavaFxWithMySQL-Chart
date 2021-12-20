package com.example.brief3javafx.controller;
import com.example.brief3javafx.Implements.ClientImpl;
import com.example.brief3javafx.Implements.NumberImpl;
import com.example.brief3javafx.Main;
import com.example.brief3javafx.dao.ClientDao;
import com.example.brief3javafx.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;


public class NewClientController {
    @FXML
    private Label regexEmail;

    @FXML
    private Label Failed;

    @FXML
    private Label regexDate;

    @FXML
    private Label regexLastName;
    @FXML
    private Label regexPassport;

    @FXML
    private Label regexCompagny;

    @FXML
    private Label regexAdress;

    @FXML
    private Label regexFirstName;

    @FXML
    private Label regexPhone;

    @FXML
    private ComboBox<String> dropdown;
    @FXML
    private TextField passport;
    @FXML
    private Label Succes;
    @FXML
    private Label regexBadge;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker date;

    @FXML
    private TextField compagny;

    @FXML
    private TextField badge;

    @FXML
    private TextField number;

    @FXML
    private TextArea adress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    void radioButtonPass(ActionEvent event) {
        cin.setVisible(false);
        passport.setVisible(true);
        cin.clear();
    }

    @FXML
    void radioButtonCin(ActionEvent event) {
        cin.setVisible(true);
        passport.setVisible(false);
        passport.clear();
    }

    @FXML
    public void initialize() throws ParseException {
        new NumberImpl().addDropDown(dropdown);
    }

    private boolean isEmail(String input){
        return  input.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]{2,3}+");
    }
    private boolean max50(String input){
       if(input.length()>50) {
           return  true;
       }
       else {
           return  false;
       }
    }
    private boolean tenChar(String input){
        if(input.length()==10) {
            return  false;
        }
        else {
            return  true;
        }
    }

    private boolean isPhone(String input){
        return  input.matches( "^[0-9]{9}$");
    }


    public void clearInputs(){
        badge.clear();
        cin.clear();
        firstName.clear();
        lastname.clear();
        adress.clear();
        passport.clear();
        cin.clear();
        number.clear();
        email.clear();
        compagny.clear();
    }
    @FXML
    void clear(ActionEvent event) {
       clearInputs();

    }
    @FXML
    void buttonListClients(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("list-clients.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 967, 568);
            stage.setTitle("Add Clients !");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addClients(ActionEvent event) throws SQLException {
        boolean res= true;

     if(!isPhone(number.getText())) {
         regexPhone.setVisible(true);
         res=false;
     }
     else{
         regexPhone.setVisible(false);
     }
     if(!isEmail(email.getText())){
        regexEmail.setVisible(true);
         res=false;
     }
     else{
         regexEmail.setVisible(false);
     }
     
     if(!tenChar(badge.getText())){
         regexBadge.setVisible(true);
         res=false;
     }
     else{
         regexBadge.setVisible(false);
     }
        if(max50(firstName.getText())){
            regexFirstName.setVisible(true);
            res=false;
        }
        if(max50(lastname.getText())){
            regexLastName.setVisible(true);
            res=false;
        }
if(res){
    String cinClt = cin.getText();
    String passportClt = passport.getText();

    if (cinClt.isEmpty()){
        cinClt = null;
    }

    if (passportClt.isEmpty()){
        passportClt = null;
    }

    Client c = new ClientImpl().addClient(badge.getText(),compagny.getText(), firstName.getText(),lastname.getText(),adress.getText(),date.getValue(),email.getText(),number.getText(),dropdown.getSelectionModel().getSelectedItem(),cinClt,passportClt);
   // NouveauxClients.AjoutClient(c);
    ClientDao cn=new ClientDao();
    boolean t = cn.ajouterClient(c);
    if(t)
    {
        Failed.setVisible(true);
        Succes.setVisible(true);
        clearInputs();
    }
    else{
        Failed.setVisible(true);
        Succes.setVisible(false);
    }

      }
    }
}
