package com.example.pedrolopes.internshapp;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by fran on 05-03-2016.
 */
public class Coordinator implements Serializable {

    String name;
    String eMail;
    int phoneNumber;
    private HashSet<Internship> internships;

    public Coordinator(String name, String email, int phoneNumber){
        this.name = name;
        this.eMail = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashSet<Internship> getInternships() {
        return internships;
    }

    public void addInternship(Internship internship) {
        this.internships.add(internship);
    }
}
