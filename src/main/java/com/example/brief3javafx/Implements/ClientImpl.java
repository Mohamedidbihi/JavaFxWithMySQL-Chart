package com.example.brief3javafx.Implements;

import com.example.brief3javafx.interfaces.IClient;
import com.example.brief3javafx.models.Client;

import java.time.LocalDate;

public class ClientImpl implements IClient {

    @Override
    public Client addClient(String badge, String compagny, String firstName, String lastName, String adress, LocalDate startDate, String email, String phone, String countryCode, String cin, String passport) {
        Client client = new Client();
        client.setBadge(badge);
        client.setCompagny(compagny);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAdress(adress);
        client.setStartDate(startDate);
        client.setEmail(email);
        client.setPhone(phone);
        client.setCountryCode(countryCode);
        client.setCin(cin);
        client.setPassport(passport);
        return  client;
    }


}
