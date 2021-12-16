
package com.example.brief3javafx.Implements;
import com.example.brief3javafx.interfaces.IEmployee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EmployeeImpl implements IEmployee {

    @Override
    public boolean logged(String email,String password) {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL("https://simplonline-v3-prod.s3.eu-west-3.amazonaws.com/media/file/json/faf36def-e51b-4a50-919b-409f7781cade.json");
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(content.toString());
            for(int i = 0; i < jsonArray.size(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String getEmail = (String) jsonObject.get("email");
                String getPassword = (String) jsonObject.get("password");

                if(email.equalsIgnoreCase(getEmail) && password.equals(getPassword)){
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
