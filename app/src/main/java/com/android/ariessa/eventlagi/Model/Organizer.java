package com.android.ariessa.eventlagi.Model;

public class Organizer {

    public String name;
    public String email;
    public String password;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Organizer.class)
    public Organizer() {
    }

    public Organizer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Setter Methods
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Getter Methods
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
