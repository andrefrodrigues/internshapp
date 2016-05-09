package com.example.pedrolopes.internshapp;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by fran on 05-03-2016.
 */
public class Company implements Serializable {

    private String name;
    private String eMail;
    private int phoneNumber;
    private HashMap<Integer, Internship> internships;
    private String address;
    // usar classe do google maps api
    private double latitude;
    // usar classe do google maps api
    private double longitude;

    public Company(String name, String eMail,int phoneNumber, String address){
        this.name = name;
        this.eMail = eMail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.internships = new HashMap<>();
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

    public HashMap<Integer, Internship> getInternships() {
        return internships;
    }

    public void setInternships(HashMap<Integer, Internship> internships) {
        this.internships = internships;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // usar classe do google maps api
    public void setCoordinates(double latitude, double longitude){ // usar classe do google maps api
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // usar classe do google maps api
    public double getLatitude(){
        return latitude;
    }

    // usar classe do google maps api
    public double getLongitude(){
        return longitude;
    }


}
