package org.example.Controller.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDashBordController {

    public void btnPlaceOrderOnACtion(ActionEvent event) {

        Parent updateRoot = null;
        try {
            updateRoot = FXMLLoader.load(getClass().getResource("/view/Order/OrderPlace.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    public void btnAddProduct(ActionEvent event) {
        Parent updateRoot = null;
        try {
            updateRoot = FXMLLoader.load(getClass().getResource("/view/Employee/AddNewProductForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

//    public void btnAddSuppliersOnAction(ActionEvent event) {
//        Parent updateRoot = null;
//        try {
//            updateRoot = FXMLLoader.load(getClass().getResource("/view/Employee/ShowSupplierForm.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Scene updateScene = new Scene(updateRoot);
//
//        Stage updateStage = new Stage();
//        updateStage.setTitle("Employee Add From");
//        updateStage.setScene(updateScene);
//        updateStage.setResizable(false);
//        updateStage.show();
//    }

    public void btnShowSupplierOnaction(ActionEvent event) {
//        Parent updateRoot = FXMLLoader.load(getClass().getResource("/"));
//        Scene updateScene = new Scene(updateRoot);
//
//        Stage updateStage = new Stage();
//        updateStage.setTitle("Employee Add From");
//        updateStage.setScene(updateScene);
//        updateStage.setResizable(false);
//        updateStage.show();
    }

    public void btnViewReport(ActionEvent event) {
    }

    public void btnAddsupOnaction(ActionEvent event) {

        Parent updateRoot = null;
        try {
            updateRoot = FXMLLoader.load(getClass().getResource("/view/Employee/ShowSupplierForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    public void btnAddSuppliersOnAction(ActionEvent event) {

        Parent updateRoot = null;
        try {
            updateRoot = FXMLLoader.load(getClass().getResource("/view/Employee/AddSupplierForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }
}
