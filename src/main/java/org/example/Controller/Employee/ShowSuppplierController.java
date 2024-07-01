package org.example.Controller.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import org.example.bo.custom.impl.userBoImpl;
import org.example.db.dbConnection;
import org.example.dto.Product;
import org.example.dto.Supplier;
import org.example.dto.Tables.supProductTb;
import org.example.util.CrudUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowSuppplierController implements Initializable {
    public TextField txtEmpID;
    public TextField txtcomap;
    public TextField txtEmail;
    public TextField txtname;
    public TableColumn colProductID;
    public TableColumn colCategory;
    public TableColumn colQTY;
    public TableColumn colTotalCharge;
    public TableView tblSuolyProduct;
    public TextField txtSupID;
    public ComboBox cmbSupId;
    public Rectangle cmbSupplierID;

    private String category;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSupplierID();

        colProductID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotalCharge.setCellValueFactory(new PropertyValueFactory<>("totalCharge"));

        txtname.setDisable(true);
        txtcomap.setDisable(true);
        txtEmail.setDisable(true);
    }


    public void bynDeleteOnAction(ActionEvent event) {
        try {

            if(dbConnection.getInstance().getConnection().createStatement().execute("DELETE FROM supplier WHERE id='" + cmbSupId.getValue().toString()+ "'"))
            {
                System.out.println("ture");
            }else
            {
                System.out.println("false");
            }

//            ShowEmployee();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clearTxt();
    }

    public void btnUpdateOnAction(ActionEvent event) {
        txtname.setDisable(false);
        txtcomap.setDisable(false);
        txtEmail.setDisable(false);
    }

    public void btnSubmitModifiOnAction(ActionEvent event) {

        System.out.println("btnSubmitModifiOnAction------------>"+category);
        String id = cmbSupId.getValue().toString();
        String name = txtname.getText();
        String email = txtEmail.getText();
        String company = txtcomap.getText();

        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setEmail(email);
        supplier.setCompany(company);
        supplier.setItem(category);
        System.out.println(supplier);

        userBoImpl userBoll = new userBoImpl();
        userBoll.updateSupplier(supplier);

    }

    private void loadSupplierID() {
        try (ResultSet resultSet = CrudUtil.execute("SELECT id FROM supplier")) {
            ObservableList<String> supIdList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                supIdList.add(resultSet.getString("id"));
            }

            cmbSupId.setItems(supIdList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearTxt()
    {
        txtname.setText(null);
        txtcomap.setText(null);
        txtEmail.setText(null);
    }

    public void btnSearchOnACtion(ActionEvent event) {
        try {
            String query = "SELECT * FROM supplier WHERE id='" + cmbSupId.getValue().toString()+ "'";
            System.out.println("Query: " + query);
            ResultSet resultSet = CrudUtil.execute(query);
            if (resultSet.next()) {
                Supplier supplierDetails = new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                System.out.println("Supplier details: " + supplierDetails);
                txtname.setText(supplierDetails.getName());
                txtEmail.setText(supplierDetails.getEmail());
                txtcomap.setText(supplierDetails.getCompany());

                ShowProductIdem();
            } else {
                System.out.println("No records found");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void ShowProductIdem()
    {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE Supplier_ID='" + cmbSupId.getValue().toString() + "'");
            ObservableList<Product> SupplierList= FXCollections.observableArrayList();
            ObservableList<supProductTb>  TableSupProductrData=FXCollections.observableArrayList();

            while (resultSet.next()){
                Product supplierDetails = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)
                );

                category = supplierDetails.getName();
                System.out.println("ShowProductIdem------------>"+category);
                SupplierList.add(supplierDetails);

                SupplierList.forEach(supplier -> {
                    supProductTb supplierTbData = new supProductTb(supplier.getId(),supplier.getId(),supplier.getName(),supplier.getQty(),supplier.getPrice());
                    TableSupProductrData.add(supplierTbData);

                });
            }
                   tblSuolyProduct.setItems(TableSupProductrData);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
