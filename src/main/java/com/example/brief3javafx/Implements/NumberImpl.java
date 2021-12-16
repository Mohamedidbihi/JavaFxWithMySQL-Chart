
package com.example.brief3javafx.Implements;

import com.example.brief3javafx.interfaces.INumber;
import javafx.scene.control.ComboBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NumberImpl implements INumber {
    @Override

    public String getDoc() {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL("https://simplonline-v3-prod.s3.eu-west-3.amazonaws.com/media/file/json/4390ae94-0a2d-4af1-8f24-30e429d788f1.json");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void addDropDown(ComboBox c) throws ParseException {


        String content = getDoc();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(content);

        for(int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String jsonDialCode = (String) jsonObject.get("dial_code");
            c.getItems().add(jsonDialCode);
        }

    }

}
