package models;

public class Admin {
    private static String name = "admin";
    private static String password = "123456";

    public Admin(){
    }

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }
}
