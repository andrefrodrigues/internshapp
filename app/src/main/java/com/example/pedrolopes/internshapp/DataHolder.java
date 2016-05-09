package com.example.pedrolopes.internshapp;

/**
 * Created by fran on 06-03-2016.
 */
public class DataHolder {

    private static Student currentStudent;
    private static Company currentCompany;
    private static String currentType;

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Student currentStudent) {
        DataHolder.currentStudent = currentStudent;
    }

    public static Company getCurrentCompany() {
        return currentCompany;
    }

    public static void setCurrentCompany(Company currentCompany) {
        DataHolder.currentCompany = currentCompany;
    }

    public static String getCurrentType() {
        return currentType;
    }

    public static void setCurrentType(String currentType) {
        DataHolder.currentType = currentType;
    }
}
