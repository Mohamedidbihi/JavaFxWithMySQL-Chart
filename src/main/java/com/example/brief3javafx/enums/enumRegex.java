package com.example.brief3javafx.enums;

public enum enumRegex {

    EMAIL("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]{2,3}+"),
    PASSWORD("^[ A-Za-z0-9_@./#&+-]{6,}$"),
    NAME("^[a-zA-Z]{3,50}$"),
    CIN("[A-Z]{2}[0-9]{6}"),
    PASSPORT("[A-Z]{2}[0-9]{7}"),
    COMPANY("[a-zA-Z0-9]{3,50}"),
    BADGE("[a-zA-Z0-9]{10}"),
    PHONE("^[0-9]{10}$"),
    ADDRESS("^[#.0-9a-zA-Z\\s,-]{5,40}$");

    private final String value;

    enumRegex(String value) {
        this.value = value;
    }

    public String getPattern() {
        return value;
    }

}
