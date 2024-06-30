package org.example.Controller.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Product;
import org.example.util.CrudUtil;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    public Label lblOrderId;
    public ComboBox cmbCategory;
    public ComboBox cmbSize;
    public TextField txtQTY;
    public ComboBox cmbSupplierId;
    public Button btnAddOnAction;
    public TextField txtPrice;
    public Label lblProductId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateOrderId();
        loadDropCategory();
        loadDropSizes();
        loadSupplierID();


    }

    private void loadDropCategory() {

        ObservableList<Object> itemsCategory = FXCollections.observableArrayList();
        itemsCategory.add("Ladies");
        itemsCategory.add("Gents");
        itemsCategory.add("Kids");

        cmbCategory.setItems(itemsCategory);
    }

    private void loadDropSizes() {

        ObservableList<Object> itemsSizes = FXCollections.observableArrayList();
        itemsSizes.add("Small");
        itemsSizes.add("Medium");
        itemsSizes.add("Large");
        itemsSizes.add("XL");
        itemsSizes.add("XXL");
        itemsSizes.add("XXXL");
        itemsSizes.add("Default");



        cmbSize.setItems(itemsSizes);
    }
    public void generateOrderId() {
        try {
            Connection connection = dbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM product");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            String id = "P" + String.format("%03d", count + 1);
            while (true) {
                boolean exists = false;
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exists = true;
                }
                if (!exists) {
                    lblProductId.setText(id);
                    break;
                } else {
                    count++;
                    id = "P" + String.format("%03d", count + 1);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadSupplierID() {
        try (ResultSet resultSet = CrudUtil.execute("SELECT id FROM supplier")) {
            ObservableList<String> supIdList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                supIdList.add(resultSet.getString("id"));
            }

            cmbSupplierId.setItems(supIdList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnAddProductOnAction(ActionEvent event) {

        Product product = new Product(
                lblProductId.getText(),
                cmbCategory.getValue().toString(),
                cmbSize.getValue().toString(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQTY.getText()),
                cmbSupplierId.getValue().toString()

        );

        System.out.println(product);

        userBoImpl userBoll = new userBoImpl();

        userBoll.saveProduct(product);
    }
}
