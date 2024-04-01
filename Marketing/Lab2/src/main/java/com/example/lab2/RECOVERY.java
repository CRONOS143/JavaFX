package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RECOVERY {

    @FXML
    private TextField Login;

    @FXML
    private TextField PASSWORD;

    @FXML
    private Button RESET;

    @FXML
    private TextField ROLES;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        RESET.setOnAction(actionEvent -> {
        String login = Login.getText();
        String Password = PASSWORD.getText();
        String Roles = ROLES.getText();
        DBQuery dbQuery = new DBQuery();

            try {
                dbQuery.Reset(login, Password, Roles);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ваші дані було збережено!");
                alert.setHeaderText("Можете закрити вікно відновлення паролю!");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}