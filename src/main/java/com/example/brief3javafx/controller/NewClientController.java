package com.example.brief3javafx.controller;
import com.example.brief3javafx.Implements.ClientImpl;
import com.example.brief3javafx.Implements.NumberImpl;
import com.example.brief3javafx.Main;
import com.example.brief3javafx.dbConnexion.dao.ClientDao;
import com.example.brief3javafx.enums.enumRegex;
import com.example.brief3javafx.helpers.Regex;
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
        String cinClt = cin.getText();
        String passportClt = passport.getText();

        boolean firstnameRegex = Regex.matchesRegex(firstName.getText(), enumRegex.NAME.getPattern());
        boolean lastnameRegex = Regex.matchesRegex(lastname.getText(), enumRegex.NAME.getPattern());
        boolean emailRegex = Regex.matchesRegex(email.getText(), enumRegex.EMAIL.getPattern());
        boolean companyRegex = Regex.matchesRegex(compagny.getText(), enumRegex.COMPANY.getPattern());
        boolean badgeRegex = Regex.matchesRegex(badge.getText(), enumRegex.BADGE.getPattern());
        boolean phoneRegex = Regex.matchesRegex(number.getText(), enumRegex.PHONE.getPattern());
        boolean addressRegex = Regex.matchesRegex(adress.getText(), enumRegex.ADDRESS.getPattern());
        boolean cinRegex = Regex.matchesRegex(cin.getText(), enumRegex.CIN.getPattern());
        boolean passRegex = Regex.matchesRegex(passport.getText(), enumRegex.PASSPORT.getPattern());

        if (firstnameRegex){
            regexFirstName.setVisible(false);
        }else{
            regexFirstName.setVisible(true);
        }

        if (lastnameRegex){
            regexLastName.setVisible(false);
        }else{
            regexLastName.setVisible(true);
        }
        if (emailRegex){
            regexEmail.setVisible(false);
        }else{
            regexEmail.setVisible(true);
        }

        if (companyRegex){
            regexCompagny.setVisible(false);
        }else{
            regexCompagny.setVisible(true);
        }
        if (badgeRegex){
            regexBadge.setVisible(false);
        }else{
            regexBadge.setVisible(true);
        }
        if (phoneRegex){
            regexPhone.setVisible(false);
        }else{
            regexPhone.setVisible(true);
        }
        if (addressRegex){
            regexAdress.setVisible(false);
        }else{
            regexAdress.setVisible(true);
        }
        if (firstnameRegex){
            regexFirstName.setVisible(false);
        }else{
            regexFirstName.setVisible(true);
        }
        if(cinRegex || passRegex){
            regexPassport.setVisible(false);
        }else{
            regexPassport.setVisible(true);
        }
        if(!dropdown.getSelectionModel().isEmpty())
        {
            regexPhone.setVisible(false);
        }else{
            regexPhone.setVisible(true);
        }
        if(date.getValue()!=null){
            regexDate.setVisible(false);
        }else{
            regexDate.setVisible(true);
        }


        if (firstnameRegex && lastnameRegex && emailRegex && companyRegex && badgeRegex && phoneRegex && addressRegex && (cinRegex || passRegex) && (!dropdown.getSelectionModel().isEmpty()) && (date.getValue() != null)){

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
                Failed.setVisible(false);
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
