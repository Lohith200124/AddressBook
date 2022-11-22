package com.example.addressbook.Activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * checking of email
 */
public class ValidationClass {
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
    public boolean passwordValid(String str){
        int upper = 0, lower = 0, number = 0, special = 0;
        if(str.length() >= 8)
        {
            for(int i = 0; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if (ch >= 'A' && ch <= 'Z')
                    upper++;
                else if (ch >= 'a' && ch <= 'z')
                    lower++;
                else if (ch >= '0' && ch <= '9')
                    number++;
                else
                    special++;
            }
            if(upper>=1&lower>=1&number>=1&special>=1){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return  false;
        }
    }
    public boolean phoneNumberValidation(String str){
        Pattern ptrn = Pattern.compile("(0|91)?[6-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(str);
//returns a boolean value
        return (match.find() && match.group().equals(str));

    }
    public boolean zipcodeValidation(String str){
        Pattern ptr = Pattern.compile("[1-9]{6}");
        Matcher match = ptr.matcher(str);
        return  (match.find()&&match.group().equals(str));
    }
}
