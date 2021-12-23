package com.example.brief3javafx.dao;

import com.example.brief3javafx.Implements.ClientImpl;
import com.example.brief3javafx.models.Client;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.time.LocalDate;

public class ClientDaoTest {
    ClientDao Cd = new ClientDao();
    @Test
    public void ajouterClient() throws SQLException {
        ClientImpl cl = new ClientImpl();
        Client c = cl.addClient("043234","youcode","med","idbihitest","plateau", LocalDate.of(2021,10,10),"med@gmail.com","123456789","+212","hh16442","Pass");
        Cd.ajouterClient(c);
        Assertions.assertFalse(Cd.AfficherClients().isEmpty());
    }

    @Test
    public void afficherClients() throws SQLException {
        Assertions.assertNotEquals(0,Cd.AfficherClients().size());
    }

    @Test
    public void filtreClient() throws SQLException {
        Assertions.assertEquals(1,Cd.FiltreClient("idbihitest").size());
    }
    @Test
    public void statistiques() {
        Assertions.assertNotEquals(0,Cd.Statistiques());
        Assertions.assertSame(null,Cd.Statistiques().getName());
    }
}