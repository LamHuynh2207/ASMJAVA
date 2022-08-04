package com.example.asmjava;

import javafx.scene.control.Alert;

import java.util.List;

public class Function {
    public static boolean IsEmployeeExisted(List<Employee> employees, Integer IdToCheck) {
        for (Employee o : employees) {
            if (IdToCheck == o.getId())
                return true;
        }
        return false;
    }

    public static void addComplete()
    {
        Alert addComplete = new Alert(Alert.AlertType.INFORMATION);
        addComplete.setTitle("Message");
        addComplete.setContentText("Successfully added!");
        addComplete.show();
    }
    public static void addField()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("Please complete all fields!");
        addFail.show();
    }

    public static void employeeAlreadyExist()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("ID already exists. Please enter a different ID!");
        addFail.show();
    }

    public static void enterAgainId()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("Please enter ID as a positive number!");
        addFail.show();
    }

    public static void enterAgainAge()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("Please enter the age range from 15 to 60 years old!");
        addFail.show();
    }

    public static void enterAgainEmail()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("Please enter the domain name with @gmail.com!");
        addFail.show();
    }

    public static void enterAgainPhoneNumber()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Message");
        addFail.setContentText("Please enter 10 numeric characters for the phone number!");
        addFail.show();
    }

    public static void updateComplete()
    {
        Alert updateComplete = new Alert(Alert.AlertType.INFORMATION);
        updateComplete.setTitle("Message");
        updateComplete.setContentText("Successfully updated!");
        updateComplete.show();
    }
    public static void updateFail()
    {
        Alert updateFail = new Alert(Alert.AlertType.ERROR);
        updateFail.setTitle("Message");
        updateFail.setContentText("Update failed!");
        updateFail.show();
    }
    public static void deleteComplete()
    {
        Alert deleteComplete = new Alert(Alert.AlertType.INFORMATION);
        deleteComplete.setTitle("Message");
        deleteComplete.setContentText("Successfully deleted!");
        deleteComplete.show();
    }
    public static void deleteFail()
    {
        Alert deleteFail = new Alert(Alert.AlertType.ERROR);
        deleteFail.setTitle("Message");
        deleteFail.setContentText("Delete failed!");
        deleteFail.show();
    }}
