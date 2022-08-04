package com.example.asmjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static File file = new File("Employee.txt");

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, Integer> ageColumn;

    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;

    private ObservableList<Employee> employeeList;

    @FXML
    public void addEmployee(ActionEvent event)
    {

        List<Employee> employees = readEmployeesListFromFile(file);

        try {
            if(tfID.getText().trim().isEmpty() ||
                    tfName.getText().trim().isEmpty() ||
                    tfEmail.getText().trim().isEmpty() ||
                    tfAge.getText().trim().isEmpty() ||
                    tfPhone.getText().trim().isEmpty())
            {
                Function.addField();
            }
            else if (Function.IsEmployeeExisted(employees,Integer.parseInt(tfID.getText().trim())))
            {
                Function.employeeAlreadyExist();
            }
            else if(Integer.parseInt(tfID.getText().trim()) < 0)
            {
                Function.enterAgainId();
            }
            else if(Integer.parseInt(tfAge.getText().trim()) < 15 || Integer.parseInt(tfAge.getText().trim()) > 60)
            {
                Function.enterAgainAge();
            }
            else if(!tfEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
            {
                Function.enterAgainEmail();
            }
            else if(!tfPhone.getText().matches("^\\d{10}$"))
            {
                Function.enterAgainPhoneNumber();
            }
            else
            {
                Employee employee = new Employee(Integer.parseInt(tfID.getText()), tfName.getText(),
                        tfEmail.getText(), Integer.parseInt(tfAge.getText()), tfPhone.getText());
                employees.add(employee);
                addEmployeesToFile(employees,file);
                Function.addComplete();
                table.getItems().add(employee);
                tfID.setText("");
                tfName.setText("");
                tfEmail.setText("");
                tfAge.setText("");
                tfPhone.setText("");
            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Message");
            addFail.setContentText("Please double-check with the numeric input fields!");
            addFail.show();
        }

    }

    @FXML
    public void onTableSelectItem(MouseEvent event)
    {
        Employee employee = table.getSelectionModel().getSelectedItem();
        if(employee != null)
        {
            tfID.setDisable(true);
            tfID.setText(idColumn.getCellData(employee).toString());
            tfName.setText(nameColumn.getCellData(employee));
            tfEmail.setText(emailColumn.getCellData(employee));
            tfAge.setText(ageColumn.getCellData(employee).toString());
            tfPhone.setText(phoneNumberColumn.getCellData(employee));
        }
    }

    @FXML
    public void updateEmployee(ActionEvent event)
    {

        try {
            employeeList = table.getItems();
            int selectedItem = Integer.parseInt(tfID.getText()) ;
            for(Employee employee: employeeList)
            {
                if(employee.getId() == selectedItem)
                {
                    try {
                        if(tfID.getText().trim().isEmpty() ||
                                tfName.getText().trim().isEmpty() ||
                                tfEmail.getText().trim().isEmpty() ||
                                tfAge.getText().trim().isEmpty() ||
                                tfPhone.getText().trim().isEmpty())
                        {
                            Function.addField();
                        }
                        else if(Integer.parseInt(tfAge.getText().trim()) < 15 ||
                                Integer.parseInt(tfAge.getText().trim()) > 60)
                        {
                            Function.enterAgainAge();
                        }
                        else if(!tfEmail.getText().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                        {
                            Function.enterAgainEmail();
                        }
                        else if(!tfPhone.getText().matches("^\\d{10}$"))
                        {
                            Function.enterAgainPhoneNumber();
                        }
                        else {
                            employee.setName(tfName.getText());
                            employee.setEmail(tfEmail.getText());
                            employee.setAge(Integer.parseInt(tfAge.getText()));
                            employee.setPhoneNumber(tfPhone.getText());
                            table.setItems(employeeList);
                            table.refresh();
                            idColumn.getCellValueFactory();
                            nameColumn.getCellValueFactory();
                            emailColumn.getCellValueFactory();
                            ageColumn.getCellValueFactory();
                            phoneNumberColumn.getCellValueFactory();
                            List<Employee> employees = table.getItems();
                            SaveEmployeesToFile(employees, file);
                            Function.updateComplete();
                        }
                    } catch (NumberFormatException e) {
                        Alert addFail = new Alert(Alert.AlertType.ERROR);
                        addFail.setTitle("Message");
                        addFail.setContentText("Please double check with the numeric input fields!");
                        addFail.show();
                    }

                }

            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Message");
            addFail.setContentText("Please choose person first");
            addFail.show();
        }
    }

    @FXML
    public void searchText(ActionEvent event)
    {
        employeeList = FXCollections.observableArrayList(readEmployeesListFromFile(file));
        List<Employee> employees = new ArrayList<>();
        for (Employee e: employeeList){
            if (e.getName().matches("(?i).*\\b" + tfSearch.getText().trim() + "\\b.*"))
                employees.add(e);
        }
        employeeList = FXCollections.observableArrayList(employees);
        table.setItems(employeeList);
        tfSearch.setText("");
    }

    @FXML
    public void deleteEmployee(ActionEvent event)
    {
        if(!tfID.isDisable())
        {
            Function.deleteFail();
        }
        else
        {
            int selectID = table.getSelectionModel().getSelectedIndex();
            table.getItems().remove(selectID);
            idColumn.getCellValueFactory();
            nameColumn.getCellValueFactory();
            emailColumn.getCellValueFactory();
            ageColumn.getCellValueFactory();
            phoneNumberColumn.getCellValueFactory();
            List<Employee> employees =  table.getItems();
            SaveEmployeesToFile(employees,file);
            Function.deleteComplete();
            tfID.setText("");
            tfName.setText("");
            tfEmail.setText("");
            tfAge.setText("");
            tfPhone.setText("");
            tfID.setDisable(false);
        }
    }

    @FXML
    public void refreshTable(ActionEvent event)
    {
        employeeList = FXCollections.observableArrayList(readEmployeesListFromFile(file));
        table.setItems(employeeList);
    }

    @FXML
    public void refreshForm()
    {
        tfID.setDisable(false);
        tfID.setText("");
        tfName.setText("");
        tfEmail.setText("");
        tfAge.setText("");
        tfPhone.setText("");
    }

    public static void addEmployeesToFile(List<Employee> employees, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Employee o : employees) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> readEmployeesListFromFile(File file) {
        List<Employee> employees = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String eachEmployee[] = line.split(", ");
                int Id = Integer.parseInt(eachEmployee[0]);
                String name = eachEmployee[1];
                String email = eachEmployee[2];
                int age = Integer.parseInt(eachEmployee[3]);
                String phoneNumber = eachEmployee[4];
                employees.add(new Employee(Id, name,email, age, phoneNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void SaveEmployeesToFile(List<Employee> employees, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Employee o : employees) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeList = FXCollections.observableArrayList(readEmployeesListFromFile(file));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        table.setItems(employeeList);
    }
}
