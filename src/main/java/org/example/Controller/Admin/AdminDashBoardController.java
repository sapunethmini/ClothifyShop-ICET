package org.example.Controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashBoardController {
    public void btnAddUserOnAction(ActionEvent event) throws IOException {
        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/Admin/RegistrationFrom.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();

    }

    public void btnShowUserOnAction(ActionEvent event) throws IOException {

        Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/Admin/ShowUserDetailsForm.fxml"));
        Scene updateScene = new Scene(updateRoot);

        Stage updateStage = new Stage();
        updateStage.setTitle("Employee Add From");
        updateStage.setScene(updateScene);
        updateStage.setResizable(false);
        updateStage.show();
    }
}
