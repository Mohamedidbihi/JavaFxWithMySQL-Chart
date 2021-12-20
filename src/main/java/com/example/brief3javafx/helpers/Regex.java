package com.example.brief3javafx.helpers;

import java.util.regex.PatternSyntaxException;

public class Regex {

        public static boolean matchesRegex(String input, String pattern){
            try {

                boolean matches = input.matches(pattern);

                if(matches) {
                    return true;
                }
            } catch (PatternSyntaxException e) {
                return false;
            }
            return false;
        }
}
