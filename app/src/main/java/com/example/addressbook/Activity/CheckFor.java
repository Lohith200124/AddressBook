package com.example.addressbook.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * checking of email
 */
public class CheckFor {
    /**
     * checks the email format and returns true or false
     * @param str
     * @return
     */
    public boolean validateEmail(String str) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        //Iterate emails array list
        //Create instance of matcher
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
