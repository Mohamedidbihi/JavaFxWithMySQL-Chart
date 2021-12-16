package com.example.brief3javafx.interfaces;

import com.example.brief3javafx.models.Client;

import java.sql.SQLException;
import java.time.LocalDate;

public interface IClient {
    public Client addClient(String badge, String compagny, String firstName, String lastName, String adress, LocalDate startDate, String email, String phone, String countryCode, String cin, String passport);


}
