package com.example.pedrolopes.internshapp;

import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by fran on 05-03-2016.
 */
public class Student {

    private String username;
    private String password;

    private int nStudent;
    private String name;
    private Date birth;
    private String address;
    private int phoneNumber;
    private TreeMap<Integer, Internship> chosenInternships;
    private HashSet<Internship> favoriteInternships;

    public Student(String username, String password, int nStudent,
                   String name, Date birth, String address, int phoneNumber){
        this.username = username;
        this.password = password;
        this.nStudent = nStudent;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        chosenInternships = new TreeMap<Integer, Internship>();
        favoriteInternships = new HashSet<Internship>();
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Date getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getnStudent() {
        return nStudent;
    }

    public void setnStudent(int nStudent) {
        this.nStudent = nStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInternsipAsFavorite(Internship internship){
        favoriteInternships.add(internship);
    }

    public void chooseInternshipPriority(int internshipPriority, Internship internship){
        chosenInternships.put(internshipPriority, internship);
    }

    public TreeMap<Integer, Internship> getChosenInterships() {
        return chosenInternships;
    }

    public HashSet<Internship> getFavoriteInterships() {
        return favoriteInternships;
    }

    public void removeFav(String name){
        favoriteInternships.remove(name);
    }


    public Internship getFavByName(String name) {
        for(Internship i: favoriteInternships){
            if(name.equals(i.getTitle()))
                return i;
        }
        return null;
    }



}