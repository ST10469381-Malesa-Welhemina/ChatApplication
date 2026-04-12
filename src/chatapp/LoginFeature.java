package chatapp;

import java.util.regex.Pattern;
/**
 * Handles all the validation rules and the user storage
 */

public class LoginFeature {
    
    // Stored user data
    private String storedUser;
    private String storedPass;
    private String storedFirst;
    private String storedLast;
    private String storedPhone;
    
 //Check username: must have _ and maximum of 5 chars 
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
  //Check the strength of the password: 8+ chars, capital letter, number,special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        
        boolean lengthOk = password.length() >= 8;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?~`].*");
        
        return lengthOk && hasCapital && hasNumber && hasSpecial;
    }
    
    // Validate SA phone: +27 followed by exactly 9 digits
    // Reference: regex pattern from https://regex101.com/
    public boolean checkCellPhoneNumber(String phone) {
        if (phone == null) return false;
        return Pattern.matches("^\\+27[0-9]{9}$", phone);
    }
    
    // Register user after all validations
    public String registerUser(String username, String password, String firstName, String lastName, String phone) {
        StringBuilder msg = new StringBuilder();
        boolean allGood = true;
        
        //Username check
        if (!checkUserName(username)) {
            msg.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
            allGood = false;
        } else {
            msg.append("Username successfully captured.\n");
        }
        
       //Password check
        if (!checkPasswordComplexity(password)) {
            msg.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
            allGood = false;
        } else {
            msg.append("Password successfully captured.\n");
        }
        
        //Phone check
        if (!checkCellPhoneNumber(phone)) {
            msg.append("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.\n");
            allGood = false;
        } else {
            msg.append("Cell phone number successfully added.\n");
        }
        
       //Save if all valid 
        if (allGood) {
            storedUser = username;
            storedPass = password;
            storedFirst = firstName;
            storedLast = lastName;
            storedPhone = phone;
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        } else {
            return msg.toString().trim();
        }
    }
    
    //Verify login credentials 
    public boolean loginUser(String username, String password) {
        if (username == null || password == null) return false;
        return username.equals(storedUser) && password.equals(storedPass);
    }
    
    //Return login status message
    public String returnLoginStatus(boolean status, String firstName, String lastName) {
        if (status) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Getters for unit tests
    public String getStoredUser() { return storedUser; }
    public String getStoredPass() { return storedPass; }
    public String getStoredFirst() { return storedFirst; }
    public String getStoredLast() { return storedLast; }
    public String getStoredPhone() { return storedPhone; }
}