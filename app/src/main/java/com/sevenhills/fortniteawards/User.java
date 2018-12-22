package com.sevenhills.fortniteawards;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public String key;
    public int P_bucksAmount;


    public User() {

    }

    public User(String name, String email, String key, int p_bucksAmount) {
        this.name = name;
        this.email = email;
        this.key = key;
        this.P_bucksAmount = p_bucksAmount;
    }
}