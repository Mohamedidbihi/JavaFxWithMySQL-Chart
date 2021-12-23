package com.example.brief3javafx.enums;

public enum enumRegex {

    EMAIL("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]{2,3}+"),
    ADDRESS("^[#.0-9a-zA-Z\\s,-]{5,40}$"),
    NAME("^[a-zA-Z]{3,50}$"),
    COMPANY("[a-zA-Z0-9]{3,50}"),
    CIN("[A-Z]{2}[0-9]{6}"),
    PASSPORT("[A-Z]{2}[0-9]{7}"),
    BADGE("[a-zA-Z0-9]{10}"),
    PHONE("^[0-9]{10}$");


    private final String value;

    enumRegex(String value) {
        this.value = value;
    }

    public String getPattern() {
        return value;
    }

}
