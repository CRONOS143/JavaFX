package com.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class REG {

    @FXML
    private TextField ADDRESS;

    @FXML
    private Button AUTHORISE;

    @FXML
    private TextField Login;

    @FXML
    private TextField NAME;

    @FXML
    private TextField OWNERSHIP_TYPE;

    @FXML
    private TextField PASSWORD;

    @FXML
    private TextField PHONE;

    @FXML
    private TextField ROLES;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
       AUTHORISE.setOnAction(actionEvent -> {
           String login = Login.getText();
           String pass = PASSWORD.getText();
           String name = NAME.getText();
           String type = OWNERSHIP_TYPE.getText();
           String address = ADDRESS.getText();
           String phone = PHONE.getText();
           String role = ROLES.getText();
           DBQuery dbHandler = new DBQuery();
           try {
               dbHandler.ADDCLient(name, type, address, phone);
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
           try {
               dbHandler.REG(login, pass, role);
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Ваші дані було збережено!");
               alert.setHeaderText("Можете закрити вікно реєстрації!");
               alert.showAndWait();
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });
    }
}
