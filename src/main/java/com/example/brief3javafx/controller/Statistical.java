package com.example.brief3javafx.controller;

import com.example.brief3javafx.Main;
import com.example.brief3javafx.dbConnexion.dao.ClientDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

import java.io.IOException;


public class Statistical {
    @FXML
    private LineChart<?, ?> lineChart;

    public void initialize(){
        ClientDao cd = new ClientDao();
        lineChart.getData().addAll(cd.Statistiques());
    }
    @FXML
    void retour(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("list-clients.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 967, 568);
            stage.setTitle("List Clients !");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void close(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void addClient(ActionEvent event) {
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
}
