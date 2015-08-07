package com.iitb.root.booksharer;

/**
 * Created by maulik on 15/3/15.
 */
public class Email {

    public static String getEmail() {
        return email;
    }

    public static String email;
    public static void setEmail(String email1){
        email=email1;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Email.username = username;
    }

    static String username;

}
