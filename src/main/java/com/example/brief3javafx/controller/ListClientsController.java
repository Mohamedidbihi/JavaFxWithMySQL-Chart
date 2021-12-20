package com.example.brief3javafx.controller;

import com.example.brief3javafx.Main;
import com.example.brief3javafx.dbConnexion.dao.ClientDao;
import com.example.brief3javafx.models.Client;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ListClientsController {
    @FXML
    private TableView<Client> tableclients;
    @FXML
    private TableColumn<Client,String> compagny;

    @FXML
    private TableColumn<Client,String> badge;

    @FXML
    private TableColumn<Client,String> adresse;

    @FXML
    private TableColumn<Client,String> firstname;

    @FXML
    private TableColumn<Client,String> code;

    @FXML
    private TableColumn<Client,String> passport;

    @FXML
    private TableColumn<Client,String> phone;

    @FXML
    private TableColumn<Client,String> cin;

    @FXML
    private TableColumn<Client,LocalDate> startdate;

    @FXML
    private TableColumn<Client,String> email;

    @FXML
    private TableColumn<Client,String> lastname;
    @FXML
    private Button filtre;


    @FXML
    private TextField inputFiltre;

    ClientDao cd = new ClientDao();


    public void initialize() throws SQLException {

        ObservableList<Client> lists = FXCollections.observableList(cd.AfficherClients());
        badge.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getBadge());});
        compagny.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getCompagny());});
        firstname.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getFirstName());});
        lastname.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getLastName());});
        phone.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getPhone());});
        startdate.setCellValueFactory(data -> {return new SimpleObjectProperty(data.getValue().getStartDate());});
        adresse.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getAdress());});
        email.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getEmail());});
        cin.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getCin());});
        passport.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getPassport());});
        code.setCellValueFactory(data -> {return new SimpleStringProperty(data.getValue().getCountryCode());});
        tableclients.setItems(lists);

    }
    @FXML
    void buttonNewClient(ActionEvent event) {
        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newClient.fxml"));
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
    void buttonClose(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void onFiltre(ActionEvent event) throws SQLException {
        ObservableList<Client> lists = FXCollections.observableList(cd.FiltreClient(inputFiltre.getText()));
        tableclients.setItems(lists);
    }
    @FXML
    void buttonStatistic(ActionEvent event) {
        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("statistical.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 967, 568);
            stage.setTitle("Statistique clients!");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
