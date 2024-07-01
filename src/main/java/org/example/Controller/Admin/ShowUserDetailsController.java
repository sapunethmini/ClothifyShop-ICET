package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Employee;
import org.example.dto.Tables.userTb;
import org.example.util.CrudUtil;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Button;



public class ShowUserDetailsController  implements Initializable {
    public TextField txtcomap;
    public TextField txtname;
    private Button btnSubmitModifi;
    public TableView TbUserDetails;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colCompany;
    public TableColumn colPassword;
    public TableColumn colEmail;
    public TextField txtEmpID;
    public Label lblName;
    public TextField txtCompnay;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtName;


    public void ShowEmployee()
    {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user");
            ObservableList<Employee> EmployeeList= FXCollections.observableArrayList();
            ObservableList<userTb>  TableEmployeeData=FXCollections.observableArrayList();

            while (resultSet.next()){
                Employee employeeDetails = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                EmployeeList.add(employeeDetails);

                EmployeeList.forEach(employee -> {
                    userTb userTbData = new userTb(employee.getId(),employee.getName() , employee.getEmail(), employee.getCompany(), employee.getPassword());
                    TableEmployeeData.add(userTbData);
                });
            }
            TbUserDetails.setItems(TableEmployeeData);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


        try {
            ShowEmployee();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //txtdield disable
        txtname.setDisable(true);
        txtEmail.setDisable(true);
        txtcomap.setDisable(true);
        txtPassword.setDisable(true);


    }

    public void btnSearchOnACtion(ActionEvent event) {
        try {
            String query = "SELECT * FROM user WHERE id='" + txtEmpID.getText() + "'";
            System.out.println("Query: " + query);
            ResultSet resultSet = CrudUtil.execute(query);
            if (resultSet.next()) {
                Employee employeeDetails = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                System.out.println("Employee details: " + employeeDetails);
                txtname.setText(employeeDetails.getName());
                txtEmail.setText(employeeDetails.getEmail());
                txtcomap.setText(employeeDetails.getCompany());
                txtPassword.setText(employeeDetails.getPassword());
            } else {
                System.out.println("No records found");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bynDeleteOnAction(ActionEvent event) {
        try {

            if(dbConnection.getInstance().getConnection().createStatement().execute("DELETE FROM user WHERE id='" + txtEmpID.getText() + "'"))
            {
                System.out.println("ture");
            }else
            {
                System.out.println("false");
            }

            ShowEmployee();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clearTxt();
    }

    public void btnUpdateOnAction(ActionEvent event) {
        txtname.setDisable(false);
        txtEmail.setDisable(false);
        txtcomap.setDisable(false);
        txtPassword.setDisable(false);



    }

    private void clearTxt()
    {
        txtEmpID.setText(null);
        txtCompnay.setText(null);
        txtEmail.setText(null);
        txtName.setText(null);
        txtPassword.setText(null);



    }

    public void btnSubmitModifiOnAction(ActionEvent event) {
        String id = txtEmpID.getText();
        String name = txtname.getText();
        String email = txtEmail.getText();
        String company = txtcomap.getText();
        String password = txtPassword.getText();

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setCompany(company);
        employee.setPassword(password);

        userBoImpl userBoll = new userBoImpl();
        userBoll.updateEmployee(employee);

    }




}
