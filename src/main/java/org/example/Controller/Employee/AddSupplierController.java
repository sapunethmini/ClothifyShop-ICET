package org.example.Controller.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Supplier;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {
    public Label lblSupplierID;
    public TextField txtSupName;
    public TextField txtSupComapny;
    public TextField txtSupEmail;
    public ComboBox cmbsupItem;


    private static AddSupplierController instance;
    public AddSupplierController(){}

    public static AddSupplierController getInstance(){
        if(instance==null){
            return instance=new AddSupplierController();
        }
        return instance;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateOrderId();
        loadDropSupitem();


    }


    private void loadDropSupitem() {

        ObservableList<Object> itemsCategory = FXCollections.observableArrayList();
        itemsCategory.add("Ladies");
        itemsCategory.add("Gents");
        itemsCategory.add("Kids");

        cmbsupItem.setItems(itemsCategory);
    }

    public void generateOrderId() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM supplier");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            String id = "S" + String.format("%03d", count + 1);
            while (true) {
                boolean exists = false;
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM supplier WHERE id = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exists = true;
                }
                if (!exists) {
                    lblSupplierID.setText(id);
                    break;
                } else {
                    count++;
                    id = "S" + String.format("%03d", count + 1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddSupOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                lblSupplierID.getText(),
                txtSupName.getText(),
                txtSupComapny.getText(),
                txtSupEmail.getText(),
                cmbsupItem.getValue().toString()
        );

        System.out.println(supplier);

        userBoImpl userBoll = new userBoImpl();

        userBoll.saveSup(supplier);
    }
}
