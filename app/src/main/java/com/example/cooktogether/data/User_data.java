package com.example.cooktogether.data;

public class User_data {
    public String Name, Sername, email;
    public User_data(){

    }
    public User_data(String Name, String email){
        this.email = email;
        this.Name = Name;
    }
    public User_data(String Name, String Sername, String email){
        this.email = email;
        this.Name = Name;
        this.Sername = Sername;
    }
}
