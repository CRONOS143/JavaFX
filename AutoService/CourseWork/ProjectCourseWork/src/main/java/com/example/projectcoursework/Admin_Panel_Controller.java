package com.example.projectcoursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Admin_Panel_Controller {

    @FXML
    private TextField Clients_phone;

    @FXML
    private Tab Auto_tab;

    @FXML
    private TextField Order_NameClient;

    @FXML
    private TextArea Order_AutoModel;

    @FXML
    private TextField Clients_middlename;

    @FXML
    private MenuItem Order_Dop;

    @FXML
    private TextField Order_Cost;

    @FXML
    private Button Auto_add;

    @FXML
    private TextField Auto_NameClient;

    @FXML
    private TextField Auto_SurnameClient;

    @FXML
    private Tab Mech_tab;

    @FXML
    private MenuItem Order_Search;

    @FXML
    private TextField Mech_Middle;

    @FXML
    private TextField Order_NumberAuto;

    @FXML
    private Button Mech_UPDATE;

    @FXML
    private TextField Order_ID;

    @FXML
    private TextField Mech_Id;

    @FXML
    private Tab Clients_tab;

    @FXML
    private TextField Auto_model;

    @FXML
    private TextField Order_ExpExecutor;

    @FXML
    private Tab Order_tab;

    @FXML
    private DatePicker Order_DateOfIssue;

    @FXML
    private TextField Auto_MiddleClient;

    @FXML
    private DatePicker Order_DateStart;

    @FXML
    private Button Mech_Search;

    @FXML
    private TextArea Order_TypeOfRepair;

    @FXML
    private Button Clients_del;

    @FXML
    private TableView<Auto> Auto_table;

    @FXML
    private TextField Mech_Surname;

    @FXML
    private Button Mech_Del;

    @FXML
    private Button Auto_Search;

    @FXML
    private MenuButton Order_Buttons;

    @FXML
    private Button Clients_Add;

    @FXML
    private TextField Auto_number;

    @FXML
    private TextField Order_SurnameClient;

    @FXML
    private TextField Auto_ID;

    @FXML
    private Button Mech_Add;

    @FXML
    private TextField Clients_surname;

    @FXML
    private DatePicker Order_DateEnd;

    @FXML
    private TextField Mech_Exp;

    @FXML
    private TextField Mech_Name;

    @FXML
    private Button Auto_Update;

    @FXML
    private DatePicker Clients_DateBirthday;

    @FXML
    private MenuItem Order_Del;

    @FXML
    private TextField Clients_id;

    @FXML
    private TextField Order_Executor;

    @FXML
    private DatePicker Order_DateOfMade;

    @FXML
    private Button Clients_search;

    @FXML
    private Button Clients_Update;

    @FXML
    private TextField Mech_Age;

    @FXML
    private TextField Clients_name;

    @FXML
    private Button Auto_Del;

    @FXML
    private TextField Order_MiddleClient;

    @FXML
    private TableView<VLASNIK> Clients_Table;

    @FXML
    private DatePicker Auto_DateOfMade;

    @FXML
    private MenuItem Order_Update;

    @FXML
    private MenuItem Order_Add;

    @FXML
    private TextField Mech_TypeOfWork;

    @FXML
    private TableView<Ordered> Order_table;

    @FXML
    private TableView<Mechanic> Mech_table;

    @FXML
    private TabPane Tab_ADMIN_PANE;
    private ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        // Робимо ліві таби та стилізуємо їх
        Mech_tab.setStyle("-fx-tab-min-width: 150;" +
                "-fx-tab-max-width: 150;" +
                "-fx-tab-min-height: 50;" +
                "-fx-tab-max-height: 50;" +
                "-fx-rotate: 90;" +
                "-fx-padding: 15 30 15 35;" +
                "-fx-color: aqua;");
        Order_tab.setStyle("-fx-tab-min-width: 150;" +
                "-fx-tab-max-width: 150;" +
                "-fx-tab-min-height: 50;" +
                "-fx-tab-max-height: 50;" +
                "-fx-rotate: 90;" +
                "-fx-padding: 15 30 15 35;" +
                "-fx-color: aqua;");
        Auto_tab.setStyle("-fx-tab-min-width: 150;" +
                "-fx-tab-max-width: 150;" +
                "-fx-tab-min-height: 50;" +
                "-fx-tab-max-height: 50;" +
                "-fx-rotate: 90;" +
                "-fx-padding: 15 30 15 35;" +
                "-fx-color: aqua;");
        Clients_tab.setStyle("-fx-tab-min-width: 150;" +
                "-fx-tab-max-width: 150;" +
                "-fx-tab-min-height: 50;" +
                "-fx-tab-max-height: 50;" +
                "-fx-rotate: 90;" +
                "-fx-padding: 15 30 15 35;" +
                "-fx-color: aqua;");

        Tab_ADMIN_PANE.setOnMouseClicked(mouseEvent -> {
            ResultSet resultSet;
            DBHANDLER dbQuery = new DBHANDLER();
            if (Clients_tab.isSelected()) {
                //Знижки
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                Clients_Table.getItems().clear();
                Clients_Table.getColumns().clear();
                try {
                    resultSet = dbQuery.Vlasnik();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        VLASNIK vlasnik = new VLASNIK(resultSet.getInt("id"),
                                resultSet.getString("Name"),
                                resultSet.getString("Surname"),
                                resultSet.getString("MiddleName"),
                                resultSet.getLong("MOBILE_PHONE"),
                                resultSet.getDate("DATE_BIRTHDAY").toLocalDate());
                        observableList.add(vlasnik);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setVlasnik();
            }
            if (Auto_tab.isSelected()) {
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                Auto_table.getItems().clear();
                Auto_table.getColumns().clear();
                try {
                    resultSet = dbQuery.Auto();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Auto auto = new Auto(resultSet.getInt("id"),
                                resultSet.getString("model"),
                                resultSet.getString("number"),
                                resultSet.getDate("DATE_OF_MADE").toLocalDate(),
                                resultSet.getString("name_1"),
                                resultSet.getString("surname_1"),
                                resultSet.getString("middleName_1"));
                        observableList.add(auto);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setAuto();
            }
            if (Mech_tab.isSelected()) {
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                Mech_table.getItems().clear();
                Mech_table.getColumns().clear();
                try {
                    resultSet = dbQuery.Mech();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Mechanic mech = new Mechanic(resultSet.getInt("id"),
                                resultSet.getString("NAME"),
                                resultSet.getString("MIDDLENAME"),
                                resultSet.getString("SURNAME"),
                                resultSet.getInt("EXPERIENCE"),
                                resultSet.getString("TYPE_WORK"),
                                resultSet.getInt("AGE"));
                        observableList.add(mech);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setMechanic();
            }

            if (Order_tab.isSelected()) {
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                Order_table.getItems().clear();
                Order_table.getColumns().clear();
                try {
                    resultSet = dbQuery.Ordered();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Ordered ordered = new Ordered(resultSet.getInt("id"),
                                resultSet.getString("MODEL"),
                                resultSet.getString("number"),
                                resultSet.getDate("date_of_made").toLocalDate(),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getString("middlename"),
                                resultSet.getDouble("cost"),
                                resultSet.getDate("DATE_OF_ISSUE").toLocalDate(),
                                resultSet.getString("TYPE_WORK"),
                                resultSet.getDate("DATE_START").toLocalDate(),
                                resultSet.getDate("DATE_END").toLocalDate(),
                                resultSet.getString("EXECUTOR"),
                                resultSet.getInt("EXPERIENCE_EXECUTOR"));
                        observableList.add(ordered);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                setOrdered();
            }

        });
        // Додаю Клієнтів
        Clients_Add.setOnAction(actionEvent -> {
            String name = Clients_name.getText();
            String Surname = Clients_surname.getText();
            String MiddleName = Clients_middlename.getText();
            int phone = Integer.parseInt(Clients_phone.getText());
            Date dateBirthday = (Date) Clients_DateBirthday.getDayCellFactory();
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.AddVlasn(name, Surname, MiddleName, phone, dateBirthday);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис створено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Додаю Автівки клієнтів
        Auto_add.setOnAction(actionEvent -> {
            String Model = Auto_model.getText();
            String Number = Auto_number.getText();
            Date DateOfMade = (Date) Auto_DateOfMade.getDayCellFactory();
            String name_client = Auto_NameClient.getText();
            String MiddleName_Client = Auto_MiddleClient.getText();
            String SurnameClient = Auto_SurnameClient.getText();
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.AddAuto(Model, Number, DateOfMade, name_client, MiddleName_Client, SurnameClient);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис створено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Додаткова інформація:

        Order_Dop.setOnAction(actionEvent -> {
            Label label = new Label("Для створення запису пам'ятайте:\n" +
                    "1. Потрібно заповнювати всі поля!\n" +
                    "2. Потрібно заповнювати поля про клієнта згідно технічного паспорту!\n" +
                    "3. Потрібно заповнювати поля про авто клієнта згідно технічного паспорту!\n" +
                    "4. В разі успішного створення заявки, ви отримаєте повідомлення про успішне створення запису!\n" +
                    "В противному випадку програма не дасть вам зробити заявку, а відповідно з вас можуть стягнути штраф!");
            label.setStyle("-fx-font-size: 16px; -fx-text-fill: #6f0005;");
            Stage warning = new Stage();
            warning.setTitle("Увага!");
            warning.setScene(new Scene(label));
            warning.setFullScreen(true);
            warning.show();
        });
        //Додаю заявки
        Order_Add.setOnAction(actionEvent -> {
            String model = Order_AutoModel.getText();
            String number = Order_NumberAuto.getText();
            Date DatOfMade = Date.valueOf(Order_DateOfMade.getValue());
            String name = Order_NameClient.getText();
            String MiddleName = Order_MiddleClient.getText();
            String Surname = Order_SurnameClient.getText();
            Double cost = Double.valueOf(Order_Cost.getText());
            String DateOfIssue = String.valueOf(Date.valueOf(Order_DateOfIssue.getValue()));
            String service = Order_TypeOfRepair.getText();
            String DateOfStart = String.valueOf(Date.valueOf(Order_DateStart.getValue()));
            String DateOfEnd = String.valueOf(Date.valueOf(Order_DateEnd.getValue()));
            String Surname_Executor = Order_Executor.getText();
            int exp = Integer.parseInt(Order_ExpExecutor.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.AddOrder(model, number, DatOfMade, name, Surname, MiddleName, cost, DateOfIssue, service, DateOfStart, DateOfEnd, Surname_Executor, exp );
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис створено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Додаю Механиків
        Mech_Add.setOnAction(actionEvent -> {
            String name = Mech_Name.getText();
            String middleName = Mech_Middle.getText();
            String Surname = Mech_Surname.getText();
            int exp = Integer.parseInt(Mech_Exp.getText());
            String Specialize = Mech_TypeOfWork.getText();
            int age = Integer.parseInt(Mech_Age.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.AddMech(name, middleName, Surname, exp, Specialize, age);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис створено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });


        //Видаляю данні про клієнтів
        Clients_del.setOnAction(actionEvent -> {
            int id = Integer.parseInt(Clients_id.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.DelVlasn(id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис Видалено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Видаляю данні про Авто
        Auto_Del.setOnAction(actionEvent -> {
            int Id = Integer.parseInt(Auto_ID.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.DelAuto(Id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис Видалено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Видаляю данні про Заявки
        Order_Del.setOnAction(actionEvent -> {
            int id = Integer.parseInt(Order_ID.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.DelOrder(id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис Видалено");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Видаляю данні про Механиків
        Mech_Del.setOnAction(actionEvent -> {
            int id = Integer.parseInt(Mech_Id.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.DelMech(id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Запис Видалено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Шукаю інформацію про клієнтів
        Clients_search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                Clients_Table.getItems().clear();
                Clients_Table.getColumns().clear();
                String id = Clients_id.getText();
                String surname = Clients_surname.getText();
                String phone = Clients_phone.getText();
                LocalDate dateOfBirthday = Clients_DateBirthday.getValue();
                Date DateBirthday = (dateOfBirthday != null) ? Date.valueOf(dateOfBirthday) : Date.valueOf("1000-01-01");
                ResultSet resultSet = dataREQ.SearchClient(id, surname, phone, DateBirthday);
                Clients_Table.getItems().clear(); // очищаем TableView от предыдущих результатов


                while (resultSet.next()) {
                    VLASNIK vlasnik = new VLASNIK(resultSet.getInt("id"),
                            resultSet.getString("Name"),
                            resultSet.getString("Surname"),
                            resultSet.getString("MiddleName"),
                            resultSet.getLong("MOBILE_PHONE"),
                            resultSet.getDate("DATE_BIRTHDAY").toLocalDate());
                    observableList.add(vlasnik);
                }
                setVlasnik();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        //Шукаю інформацію про автівки
        Auto_Search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                Auto_table.getItems().clear();
                Auto_table.getColumns().clear();
                String id =Auto_ID.getText();
                String model = Auto_model.getText();
                String number = Auto_number.getText();
                String surname = Auto_SurnameClient.getText();
                ResultSet resultSet = dataREQ.SearchAuto(id, model, number, surname);
                Auto_table.getItems().clear(); // очищаем TableView от предыдущих результатов
                while (resultSet.next()) {
                    Auto auto = new Auto(resultSet.getInt("id"),
                            resultSet.getString("model"),
                            resultSet.getString("number"),
                            resultSet.getDate("DATE_OF_MADE").toLocalDate(),
                            resultSet.getString("name_1"),
                            resultSet.getString("surname_1"),
                            resultSet.getString("middleName_1"));
                    observableList.add(auto);
                }
                setAuto();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        //Шукаю інформацію про заявки
        Order_Search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                Order_table.getItems().clear();
                Order_table.getColumns().clear();
                String id = Order_ID.getText();
                String model = Order_AutoModel.getText();
                String surname = Order_SurnameClient.getText();

                LocalDate dateOfIssue = Order_DateOfIssue.getValue();
                Date DateOfIssue = (dateOfIssue != null) ? Date.valueOf(dateOfIssue) : Date.valueOf("1000-01-01");

                LocalDate dateStart = Order_DateStart.getValue();
                Date DateStart = (dateStart != null) ? Date.valueOf(dateStart) : Date.valueOf("1000-01-01");

                LocalDate dateEnd = Order_DateEnd.getValue();
                Date DateEnd = (dateEnd != null) ? Date.valueOf(dateEnd) : Date.valueOf("1000-01-01");

                String executor = Order_Executor.getText();
                ResultSet resultSet = dataREQ.SearcOrder(id, model, surname, DateOfIssue, DateStart, DateEnd, executor);
                Order_table.getItems().clear(); // очищаем TableView от предыдущих результатов


                while (resultSet.next()) {
                    Ordered ordered = new Ordered(resultSet.getInt("id"),
                            resultSet.getString("MODEL"),
                            resultSet.getString("number"),
                            resultSet.getDate("date_of_made").toLocalDate(),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("middlename"),
                            resultSet.getDouble("cost"),
                            resultSet.getDate("DATE_OF_ISSUE").toLocalDate(),
                            resultSet.getString("TYPE_WORK"),
                            resultSet.getDate("DATE_START").toLocalDate(),
                            resultSet.getDate("DATE_END").toLocalDate(),
                            resultSet.getString("EXECUTOR"),
                            resultSet.getInt("EXPERIENCE_EXECUTOR"));
                    observableList.add(ordered);
                }
                setOrdered();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        //Шукаю інформацію про механиків
        Mech_Search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                Mech_table.getItems().clear();
                Mech_table.getColumns().clear();
                String id = Mech_Id.getText();
                String surname = Mech_Surname.getText();
                String type_work = Mech_TypeOfWork.getText();
                String age = Mech_Age.getText();
                ResultSet resultSet = dataREQ.SearchMech(id, surname, type_work, age);
                Mech_table.getItems().clear(); // очищаем TableView от предыдущих результатов


                while (resultSet.next()) {
                    Mechanic mech = new Mechanic(resultSet.getInt("id"),
                            resultSet.getString("NAME"),
                            resultSet.getString("MIDDLENAME"),
                            resultSet.getString("SURNAME"),
                            resultSet.getInt("EXPERIENCE"),
                            resultSet.getString("TYPE_WORK"),
                            resultSet.getInt("AGE"));
                    observableList.add(mech);
                }
                setMechanic();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        // Оновлюю інформацію про клієнтів
        Clients_Update.setOnAction(actionEvent -> {
            String name = Clients_name.getText();
            String Surname = Clients_surname.getText();
            String MiddleName = Clients_middlename.getText();
            int phone = Integer.parseInt(Clients_phone.getText());
            Date dateBirthday = (Date) Clients_DateBirthday.getDayCellFactory();
            int id = Integer.parseInt(Clients_id.getText());
            DBHANDLER dbhandler = new DBHANDLER();
            try {
                dbhandler.UpdVlasn(name, Surname, MiddleName, phone, dateBirthday, id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Данні оновлено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Оновлюю інформацію про автівки
        Auto_Update.setOnAction(actionEvent -> {
            String Model = Auto_model.getText();
            String Number = Auto_number.getText();
            Date DateOfMade = (Date) Auto_DateOfMade.getDayCellFactory();
            int id = Integer.parseInt(Auto_ID.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.UpdAuto(Model, Number, DateOfMade, id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Данні оновлено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Оновлюю інформацію по заявкам
        Order_Update.setOnAction(actionEvent -> {
            String Surname = Order_SurnameClient.getText();
            Date DateOfIssue = Date.valueOf(Order_DateOfIssue.getValue());
            String service = Order_TypeOfRepair.getText();
            Date DateOfStart = Date.valueOf(Order_DateStart.getValue());
            Date DateOfEnd = Date.valueOf(Order_DateEnd.getValue());
            String Surname_Executor = Order_Executor.getText();
            int expExecutor = Integer.parseInt(Order_ExpExecutor.getText());
            int id = Integer.parseInt(Order_ID.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.UpdOrder(Surname, DateOfIssue, service, DateOfStart, DateOfEnd, Surname_Executor, expExecutor, id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Данні оновлено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Оновлюю інформацію про механиків
        Mech_UPDATE.setOnAction(actionEvent -> {
            String name = Mech_Name.getText();
            String middleName = Mech_Middle.getText();
            String Surname = Mech_Surname.getText();
            int exp = Integer.parseInt(Mech_Exp.getText());
            String Specialize = Mech_TypeOfWork.getText();
            int id = Integer.parseInt(Mech_Id.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.UpdMech(name, middleName, Surname, exp, Specialize, id);
                // Показать сообщение об успешном создании записи
                Label label = new Label("Данні оновлено!");
                label.setStyle("-fx-font-size: 16px; -fx-text-fill: #01cc32;");
                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(label));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void setVlasnik() {
        System.out.println(observableList.toString());
        Clients_Table.setItems(observableList);

        TableColumn<VLASNIK, Integer> IDS = new TableColumn<>("Identy");
        IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<VLASNIK, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<VLASNIK, String> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));

        TableColumn<VLASNIK, String> middleName = new TableColumn<>("MiddleName");
        middleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

        TableColumn<VLASNIK, Long> mobilePhone = new TableColumn<>("Mobile Phone");
        mobilePhone.setCellValueFactory(new PropertyValueFactory<>("MobilePhone"));

        TableColumn<VLASNIK, LocalDate> dateBirthday = new TableColumn<>("Date Birthday");
        dateBirthday.setCellValueFactory(new PropertyValueFactory<>("DateBirthday"));

        Clients_Table.getColumns().addAll(IDS, name, surname, middleName, mobilePhone, dateBirthday);
    }

    private void setAuto() {
        System.out.println(observableList.toString());
        Auto_table.setItems(observableList);

        TableColumn<Auto, Integer> IDS = new TableColumn<>("Identy");
        IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Auto, String> model = new TableColumn<>("Model");
        model.setCellValueFactory(new PropertyValueFactory<>("Model"));

        TableColumn<Auto, String> number = new TableColumn<>("Number");
        number.setCellValueFactory(new PropertyValueFactory<>("Number"));

        TableColumn<Auto, LocalDate> dateOfMade = new TableColumn<>("Date Of Made");
        dateOfMade.setCellValueFactory(new PropertyValueFactory<>("Date_Of_Made"));

        TableColumn<Auto, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name_1"));

        TableColumn<Auto, String> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname_1"));

        TableColumn<Auto, String> middleName = new TableColumn<>("MiddleName");
        middleName.setCellValueFactory(new PropertyValueFactory<>("middleName_1"));

        Auto_table.getColumns().addAll(IDS, model, number, dateOfMade, name, surname, middleName);
    }

    private void setMechanic() {
        System.out.println(observableList.toString());
        Mech_table.setItems(observableList);

        TableColumn<Mechanic, Integer> IDS = new TableColumn<>("Identy");
        IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Mechanic, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Mechanic, String> middleName = new TableColumn<>("MiddleName");
        middleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

        TableColumn<Mechanic, String> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));

        TableColumn<Mechanic, Integer> experience = new TableColumn<>("Experience");
        experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));

        TableColumn<Mechanic, String> typeOfWork = new TableColumn<>("Type Of Work");
        typeOfWork.setCellValueFactory(new PropertyValueFactory<>("TypeOfWork"));

        TableColumn<Mechanic, Integer> age = new TableColumn<>("Age");
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));

        Mech_table.getColumns().addAll(IDS, name, middleName, surname, experience, typeOfWork, age);
    }

    private void setOrdered() {
        System.out.println(observableList.toString());
        Order_table.setItems(observableList);

        TableColumn<Ordered, Integer> IDS = new TableColumn<>("Identy");
        IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Ordered, String> model = new TableColumn<>("Model");
        model.setCellValueFactory(new PropertyValueFactory<>("Model"));

        TableColumn<Ordered, String> number = new TableColumn<>("Number");
        number.setCellValueFactory(new PropertyValueFactory<>("Number"));

        TableColumn<Ordered, LocalDate> dateOfMade = new TableColumn<>("Date Of Made");
        dateOfMade.setCellValueFactory(new PropertyValueFactory<>("DateOfMade"));

        TableColumn<Ordered, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Ordered, String> surname = new TableColumn<>("Surname");
        surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));

        TableColumn<Ordered, String> middleName = new TableColumn<>("MiddleName");
        middleName.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

        TableColumn<Ordered, Double> cost = new TableColumn<>("Cost");
        cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));

        TableColumn<Ordered, LocalDate> dateOfIssue = new TableColumn<>("Date Of Issue");
        dateOfIssue.setCellValueFactory(new PropertyValueFactory<>("DateOfIssue"));

        TableColumn<Ordered, String> typeOfWork = new TableColumn<>("Type Of Work");
        typeOfWork.setCellValueFactory(new PropertyValueFactory<>("TypeOfWork"));

        TableColumn<Ordered, LocalDate> dateStart = new TableColumn<>("Date Start");
        dateStart.setCellValueFactory(new PropertyValueFactory<>("DateStart"));

        TableColumn<Ordered, LocalDate> dateEnd = new TableColumn<>("Date End");
        dateEnd.setCellValueFactory(new PropertyValueFactory<>("DateEnd"));

        TableColumn<Ordered, String> executor = new TableColumn<>("Executor");
        executor.setCellValueFactory(new PropertyValueFactory<>("Executor"));

        TableColumn<Ordered, Integer> experienceExecutor = new TableColumn<>("Experience Executor");
        experienceExecutor.setCellValueFactory(new PropertyValueFactory<>("ExperienceExecutor"));

        Order_table.getColumns().addAll(IDS, model, number, dateOfMade, name, surname, middleName,
                cost, dateOfIssue, typeOfWork, dateStart, dateEnd, executor, experienceExecutor);
    }

}

