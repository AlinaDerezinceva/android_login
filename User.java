
package com.example.app_login;

import java.util.UUID;

public class User {
    private String patr;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String id;

    public User(String patr, String name, String surname, String login, String password) {
        this.patr = patr;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }


    public User() {
        this.patr = "";
        this.name= "";
        this.surname = "";
        this.login = "";
        this.password = "";
    }


    User user = new User();





    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return id;
    }


    public void setID(String id) {
        this.id = id;
    }
}

