package com.example.lab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private ButtonBar BUTTONBAR;

    @FXML
    private TextField Login;

    @FXML
    private Button OPEN;

    @FXML
    private PasswordField PASSWORD;

    @FXML
    private Button REG;

    @FXML
    private Button RESET;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        OPEN.setOnAction(actionEvent -> {
            String login = Login.getText();
            String password = PASSWORD.getText();
            DBQuery dbHandler = new DBQuery();
            try {
                ResultSet resultSet = dbHandler.Authorise(login, password);
                if (resultSet.next()) {
                    // Пользователь авторизован
                    String role = resultSet.getString("Role");

                    // Закрываем текущее окно
                    Stage stage = (Stage) OPEN.getScene().getWindow();
                    stage.close();

                    if (role.equals("Client")) {
                        // Пример: создаем новое окно с заголовком "Клиент"
                        Stage clientStage = new Stage();
                        clientStage.setTitle("Клієнт");

                        // Создаем сцену для клиента и устанавливаем ее на окно
                        Parent clientScene = FXMLLoader.load(getClass().getResource("Client.fxml"));
                        clientStage.setScene(new Scene(clientScene));
                        clientStage.show();

                    } else if (role.equals("admin")) {
                        // Пример: создаем новое окно с заголовком "Техник"
                        Stage analitic = new Stage();
                        analitic.setTitle("Аналітична");

                        // Создаем сцену для техника и устанавливаем ее на окно
                        Parent analyticScene = FXMLLoader.load(getClass().getResource("Analitics.fxml"));
                        analitic.setScene(new Scene(analyticScene));
                        analitic.show();
                        analyticScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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

        RESET.setOnAction(actionEvent -> {
            // Создаем новое окно с заголовком "Восстановление пароля"
            Stage resetStage = new Stage();
            resetStage.setTitle("Відновлення паролю");

            // Создаем сцену для восстановления пароля и устанавливаем ее на окно
            Parent resetScene = null;
            try {
                resetScene = FXMLLoader.load(getClass().getResource("recovery.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            resetStage.setScene(new Scene(resetScene));
            resetStage.show();
        });
        REG.setOnAction(actionEvent -> {
            // Создаем новое окно с заголовком "Реєстрація"
            Stage registr = new Stage();
            registr.setTitle("Реєстрація");

            // Создаем сцену для восстановления пароля и устанавливаем ее на окно
            Parent registrScene = null;
            try {
                registrScene = FXMLLoader.load(getClass().getResource("Registr.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            registr.setScene(new Scene(registrScene));
            registr.show();
        });
    }
}