import java.io.*;
import java.util.Scanner;

public class User
{
    private String userID,userName,userPassword,phoneNumber,location;

    public User(String userName, String userID, String phoneNumber, String location,
                String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
