package com.example.brief3javafx.interfaces;
import javafx.scene.control.ComboBox;
import org.json.simple.parser.ParseException;

public interface INumber {
    public String getDoc();
    public void addDropDown(ComboBox c) throws ParseException;
}
