package org.example.Controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.bo.custom.UserBo;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Employee;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegistartionController  implements Initializable {


    public TextField txtName;
    public TextField txtEmail;
    public TextField txtComapny;
    public TextField txtPassword;
    public Label lblUserId;


    public void btnRegisterEmployee(ActionEvent event) {
        Employee employee = new Employee(
                lblUserId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtComapny.getText(),
                txtPassword.getText()
        );

        System.out.println(employee);

        userBoImpl userBoll = new userBoImpl();

        userBoll.saveUser(employee);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        generateOrderId();

    }

    public void generateOrderId() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM user");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            String id = "D" + String.format("%03d", count + 1);
            while (true) {
                boolean exists = false;
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exists = true;
                }
                if (!exists) {
                    lblUserId.setText(id);
                    break;
                } else {
                    count++;
                    id = "D" + String.format("%03d", count + 1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
