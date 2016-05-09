package com.example.pedrolopes.internshapp;

import java.io.Serializable;

/**
 * Created by fran on 05-03-2016.
 */
public class Internship implements Serializable {

    private Company company;
    private int id;
    private String title;
    private String info;
    private Coordinator coordinator;

    public Internship(int id, String title, String info, Coordinator coordinator, Company company){
        this.id = id;
        this.title = title;
        this.info = info;
        this.coordinator = coordinator;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
