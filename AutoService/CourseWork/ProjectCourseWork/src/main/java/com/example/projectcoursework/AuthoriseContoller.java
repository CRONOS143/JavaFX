package com.example.projectcoursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthoriseContoller {

    @FXML
    private Button confirm;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;
    private ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        confirm.setOnAction(actionEvent -> {
            String login1 = login.getText();
            String password2 = password.getText();
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                ResultSet resultSet = dbHandler.Authorise(login1, password2);
                if (resultSet.next()) {
                    // Пользователь авторизован
                    String role = resultSet.getString("Role");

                    // Закрываем текущее окно
                    Stage stage = (Stage) confirm.getScene().getWindow();
                    stage.close();

                    if (role.equals("admin")) {
                        // Пример: создаем новое окно с заголовком "Адміністратора"
                        Stage AdminWin = new Stage();
                        AdminWin.setTitle("Адмінка");

                        // Создаем сцену для Адміністратора и устанавливаем ее на окно
                        Parent AdminScene = FXMLLoader.load(getClass().getResource("Administrator.fxml"));
                        AdminWin.setScene(new Scene(AdminScene));
                        AdminWin.setFullScreen(true);
                        AdminWin.show();

                    } else if (role.equals("SysAdmin")) {
                        // Пример: создаем новое окно с заголовком "СистемногоАдміністратора"
                        Stage Sys_Admin = new Stage();
                        Sys_Admin.setTitle("Системна");

                        // Создаем сцену для СистемногоАдміністратора и устанавливаем ее на окно
                        Parent Sys_admin_scene = FXMLLoader.load(getClass().getResource("SysAdminPanel.fxml"));
                        Sys_Admin.setScene(new Scene(Sys_admin_scene));
                        Sys_Admin.setFullScreen(true);
                        Sys_Admin.show();

                    }
                } else {
                    // Пример: создаем всплывающее окно с сообщением об ошибке
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Помилка авторизації!");
                    alert.setHeaderText("Перевірте правильність введених даних, логіну та паролю!");
                    alert.showAndWait();

                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}