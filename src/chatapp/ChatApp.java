package chatapp;

import java.util.Scanner;

//ChatApp - Main application for registration and login

public class ChatApp {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LoginFeature auth = new LoginFeature();
        
      //Personal details
        String firstName, lastName;
        String username, password, phone;
        
    //Welcome screen
        System.out.println("======================================");
        System.out.println("            WELCOME TO CHATAPP");
        System.out.println("======================================");
        
        //Get user's real name
        System.out.print("Enter first name: ");
        firstName = scan.nextLine();
        
        System.out.print("Enter last name: ");
        lastName = scan.nextLine();
        System.out.println();
        
       //---- User validation loop ----
        do {
            System.out.print("Enter username (must contain _ and be ≤5 chars): ");
            username = scan.nextLine();
            
            if (!auth.checkUserName(username)) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!auth.checkUserName(username));
        System.out.println("Username successfully captured.\n");
        
        //---- Password validation loop ----
        do {
            System.out.print("Enter password (8+ chars, 1 capital, 1 number, 1 special): ");
            password = scan.nextLine();
            
            if (!auth.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!auth.checkPasswordComplexity(password));
        System.out.println("Password successfully captured.\n");
        
     //---- Phone validation loop ----
        do {
            System.out.print("Enter cell phone number (+27 followed by 9 digits): ");
            phone = scan.nextLine();
            
            if (!auth.checkCellPhoneNumber(phone)) {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.");
            }
        } while (!auth.checkCellPhoneNumber(phone));
        System.out.println("Cell phone number successfully added.\n");
        
       //Register user
        String regMsg = auth.registerUser(username, password, firstName, lastName, phone);
        System.out.println(regMsg);
        
       //---- Login section ----
        System.out.println("====================================");
        System.out.println("              LOGIN SECTION" );
        System.out.println("====================================");
        
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String loginUser = scan.nextLine();
            
            System.out.print("Enter password: ");
            String loginPass = scan.nextLine();
            
            loggedIn = auth.loginUser(loginUser, loginPass);
            
            if (loggedIn) {
                System.out.println("\n" + auth.returnLoginStatus(true, firstName, lastName));
            } else {
                System.out.println("Username or password incorrect, please try again.\n");
            }
        }
        
        System.out.println("==============================================");
        System.out.println("        You are now logged into ChatApp");
        System.out.println("==============================================");
        
       
    }
}