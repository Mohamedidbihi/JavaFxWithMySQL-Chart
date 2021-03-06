package com.example.brief3javafx.dao;


import com.example.brief3javafx.dbConnexion.Connexion;
import com.example.brief3javafx.models.Client;

import com.example.brief3javafx.models.Employee;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
//import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public  class ClientDao   {

   // final static Logger log = Logger.getLogger(ClientDao.class.getName());

    EmployeDao d =  new EmployeDao();
    Employee employeC = d.auth;

    public boolean ajouterClient(Client c) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query_CHECK = "SELECT * from client where cin = ? or passport = ? or numBadge = ?";
            statement = connection.prepareStatement(query_CHECK);
            statement.setString(1, c.getCin());
            statement.setString(2, c.getPassport());
            statement.setString(3, c.getBadge());
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            if(rs.next())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerte");
                alert.setHeaderText("Attention !!:");
                alert.setContentText("Utulisateur déja existe !!");
                alert.showAndWait();
                //log.warn("Utulisateur existe deja [badge]:"+c.getBadge()+"");
                return false;
            }
            else {
                String query_POST = "INSERT INTO mutuellecentr.client(numBadge,compagny,startWork,firstName,lastName,cin,Passport,countryCode,phone,email,adress)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
                statement = connection.prepareStatement(query_POST, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, c.getBadge());
                statement.setString(2, c.getCompagny());
                statement.setDate(3, Date.valueOf(c.getStartDate()));
                statement.setString(4, c.getFirstName());
                statement.setString(5, c.getLastName());
                statement.setString(6, c.getCin());
                statement.setString(7, c.getPassport());
                statement.setString(8, c.getCountryCode());
                statement.setString(9, c.getPhone());
                statement.setString(10, c.getEmail());
                statement.setString(11, c.getAdress());
                statement.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            //log.error("Erreur de ajoute de client [id]="+employeC.getId()+" [email]="+employeC.getEmail()+"");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return false;
    }

    public List<Client> AfficherClients() throws SQLException{
        List<Client> clients = new ArrayList<Client>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query_POST = "SELECT * from client";
            statement = connection.prepareStatement(query_POST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Client A = new Client();
                A.setBadge(rs.getString(1));
                A.setCompagny(rs.getString(2));
                A.setStartDate(rs.getDate(3).toLocalDate());
                A.setFirstName(rs.getString(4));
                A.setLastName(rs.getString(5));
                A.setCin(rs.getString(6));
                A.setPassport(rs.getString(7));
                A.setCountryCode(rs.getString(8));
                A.setPhone(rs.getString(9));
                A.setAdress(rs.getString(10));
                A.setEmail(rs.getString(11));
                clients.add(A);

            }

        } catch (ClassNotFoundException | SQLException e) {
            //log.warn("Erreur de affichage des clients [id]:"+employeC.getId()+" [email]="+employeC.getEmail()+"");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return  clients;

    }
    public List<Client> FiltreClient(String text) throws SQLException {
        List<Client> clients = new ArrayList<Client>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connexion.getConnection();
            String query_POST = "SELECT * From client where  (cin IS NULL OR cin Like '"+text+"') or( passport IS NULL OR passport Like'"+text+"') or( email IS NULL OR email Like'%"+text+"%')or( firstName IS NULL OR firstName Like'%"+text+"%') or( lastName IS NULL OR lastName Like'%"+text+"%')";
            statement = connection.prepareStatement(query_POST);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Client A = new Client();
                A.setBadge(rs.getString(1));
                A.setCompagny(rs.getString(2));
                A.setStartDate(rs.getDate(3).toLocalDate());
                A.setFirstName(rs.getString(4));
                A.setLastName(rs.getString(5));
                A.setCin(rs.getString(6));
                A.setPassport(rs.getString(7));
                A.setCountryCode(rs.getString(8));
                A.setPhone(rs.getString(9));
                A.setAdress(rs.getString(10));
                A.setEmail(rs.getString(11));
                clients.add(A);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //log.warn("Erreur de filtrage [id]="+employeC.getId()+" [email]="+employeC.getEmail()+"");
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return clients;
    }
    public XYChart.Series Statistiques()
    {

        XYChart.Series series = new XYChart.Series();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = Connexion.getConnection();
            String Query = "  SELECT MONTHNAME(created_at) ,MONTH(created_at),count(*) FROM client   GROUP BY  MONTH(created_at)ORDER BY MONTH(created_at)";
            statement = connection.prepareStatement(Query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data(rs.getString(1),rs.getDouble(3)));
            }
            Platform.runLater(()
                    -> {
                series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke:#459fe1;");
            });
        }catch(Exception e){
            //log.warn("Erreur de connexion [id]="+employeC.getId()+" [email]="+employeC.getEmail()+"");
            System.out.println("Error on DB connection");
        }
return series;
    }
}
