package com.example.projectcoursework;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class SystemAdminPanel_controller {

    @FXML
    private TextField Authorise_ID;

    @FXML
    private TextField Order_Executor;

    @FXML
    private MenuItem Authorise_SetAuthorise;

    @FXML
    private MenuItem Order_Dop;

    @FXML
    private TextField Order_ClientName;

    @FXML
    private DatePicker Order_DateOfMade;

    @FXML
    private TextField Order_Cost;

    @FXML
    private TableView<Ordered> Order_Table;

    @FXML
    private TextField Order_TypeOfWork;

    @FXML
    private MenuItem Order_Search;

    @FXML
    private TextField Order_Number;

    @FXML
    private MenuItem Authorise_Del;

    @FXML
    private TextField Authorise_role;

    @FXML
    private MenuItem Authorise_Search;

    @FXML
    private MenuButton Order_MenuButton;

    @FXML
    private TextField Order_Model;

    @FXML
    private DatePicker Order_DateOfIssue;

    @FXML
    private TextField Order_ID;

    @FXML
    private TextField Order_ExpExecutor;

    @FXML
    private MenuButton Authorise_MenuButton;

    @FXML
    private DatePicker Order_DateStart;

    @FXML
    private MenuItem Authorise_Update;

    @FXML
    private TableView<Authorise> Authorise_Table;

    @FXML
    private TextField Authorise_Password;

    @FXML
    private MenuItem WINDOWS_CLOSE;
    @FXML
    private MenuItem Order_Set;

    @FXML
    private TextField Order_ClientMiddle;

    @FXML
    private TextField Order_ClientSurname;

    @FXML
    private TextField Authorise_Login;

    @FXML
    private MenuItem Authorise_Add;

    @FXML
    private DatePicker Order_DateEnd;

    private ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
            setOrdered();
            setAuthorise();
            //Працюю з обліковими записами:
        //Додаю
        Authorise_Add.setOnAction(actionEvent -> {
            String login = Authorise_Login.getText();
            String Pass = Authorise_Password.getText();
            String Role = Authorise_role.getText();
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.addAuthorise(login, Pass, Role);
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
        //Оновлююю
        Authorise_Update.setOnAction(actionEvent -> {
            String login = Authorise_Login.getText();
            String Pass = Authorise_Password.getText();
            String Role = Authorise_role.getText();
            int id = Integer.parseInt(Authorise_ID.getText());
            DBHANDLER dbhandler = new DBHANDLER();
            try {
                dbhandler.updateAuthorise(login, Pass, Role, id);
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
        //Видаляю
        Authorise_Del.setOnAction(actionEvent -> {
            int id = Integer.parseInt(Authorise_ID.getText());
            DBHANDLER dbHandler = new DBHANDLER();
            try {
                dbHandler.deleteAuthorise(id);
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
        //Шукаю
        Authorise_Search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                Authorise_Table.getItems().clear();
                Authorise_Table.getColumns().clear();
                String id = Authorise_ID.getText();
                String login = Authorise_Login.getText();
                String pass = Authorise_Password.getText();
                String Role = Authorise_role.getText();
                ResultSet resultSet = dataREQ.searchAuthorise(id, login, pass, Role);
                Authorise_Table.getItems().clear(); // очищаем TableView от предыдущих результатов


                while (resultSet.next()) {
                    Authorise authorise = new Authorise(resultSet.getInt("id"),
                            resultSet.getString("Login"),
                            resultSet.getString("PASS"),
                            resultSet.getString("ROLE"));
                    observableList.add(authorise);
                }
                System.out.println(observableList.toString());
                Authorise_Table.setItems(observableList);

                TableColumn<Authorise, Integer> IDS = new TableColumn<>("Identy");
                IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Authorise, String> Login = new TableColumn<>("login");
                Login.setCellValueFactory(new PropertyValueFactory<>("Login"));

                TableColumn<Authorise, String> Passw = new TableColumn<>("Password");
                Passw.setCellValueFactory(new PropertyValueFactory<>("password"));

                TableColumn<Authorise, String> role = new TableColumn<>("Role");
                role.setCellValueFactory(new PropertyValueFactory<>("Role"));

                Authorise_Table.getColumns().addAll(IDS, Login, Passw, role);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        //Шукаю заявки
        Order_Search.setOnAction(actionEvent -> {
            DBHANDLER dataREQ = new DBHANDLER();
            try {
                String id = Order_ID.getText();
                String model = Order_Model.getText();
                String number = Order_Number.getText();
                String name = Order_ClientName.getText();
                String middleName = Order_ClientMiddle.getText();
                String Surname = Order_ClientSurname.getText();
                String service = Order_TypeOfWork.getText();
                String Cost = Order_Cost.getText();
                String Surname_Executor = Order_Executor.getText();
                String expExecutor = Order_ExpExecutor.getText();

                LocalDate dateOfMade = Order_DateOfMade.getValue();
                Date DaTeOfMade = (dateOfMade != null) ? Date.valueOf(dateOfMade) : Date.valueOf("1000-01-01");

                LocalDate dateOfIssue = Order_DateOfIssue.getValue();
                Date DateOfIssue = (dateOfIssue != null) ? Date.valueOf(dateOfIssue) : Date.valueOf("1000-01-01");

                LocalDate dateStart = Order_DateStart.getValue();
                Date DateStart = (dateStart != null) ? Date.valueOf(dateStart) : Date.valueOf("1000-01-01");

                LocalDate dateEnd = Order_DateEnd.getValue();
                Date DateEnd = (dateEnd != null) ? Date.valueOf(dateEnd) : Date.valueOf("1000-01-01");


                ResultSet resultSet = dataREQ.SearcOrder_SysAdmin(id, model, number, DaTeOfMade, name, Surname, middleName,
                        Cost, DateOfIssue, service, DateStart, DateEnd, Surname_Executor, expExecutor);
                Order_Table.getItems().clear(); // очищаем TableView от предыдущих результатов


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
                System.out.println(observableList.toString());
                Order_Table.setItems(observableList);

                TableColumn<Ordered, Integer> IDS = new TableColumn<>("Identy");
                IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Ordered, String> MODEL = new TableColumn<>("Model");
                MODEL.setCellValueFactory(new PropertyValueFactory<>("Model"));

                TableColumn<Ordered, String> numbere = new TableColumn<>("Number");
                numbere.setCellValueFactory(new PropertyValueFactory<>("Number"));

                TableColumn<Ordered, LocalDate> dateOfMadee = new TableColumn<>("Date Of Made");
                dateOfMadee.setCellValueFactory(new PropertyValueFactory<>("DateOfMade"));

                TableColumn<Ordered, String> namee = new TableColumn<>("Name");
                namee.setCellValueFactory(new PropertyValueFactory<>("Name"));

                TableColumn<Ordered, String> SURNAME = new TableColumn<>("Surname");
                SURNAME.setCellValueFactory(new PropertyValueFactory<>("Surname"));

                TableColumn<Ordered, String> middleNamee = new TableColumn<>("MiddleName");
                middleNamee.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

                TableColumn<Ordered, Double> cost = new TableColumn<>("Cost");
                cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));

                TableColumn<Ordered, LocalDate> dateOfIssue1 = new TableColumn<>("Date Of Issue");
                dateOfIssue1.setCellValueFactory(new PropertyValueFactory<>("DateOfIssue"));

                TableColumn<Ordered, String> typeOfWork = new TableColumn<>("Type Of Work");
                typeOfWork.setCellValueFactory(new PropertyValueFactory<>("TypeOfWork"));

                TableColumn<Ordered, LocalDate> dateStart1 = new TableColumn<>("Date Start");
                dateStart1.setCellValueFactory(new PropertyValueFactory<>("DateStart"));

                TableColumn<Ordered, LocalDate> DaTeEnD = new TableColumn<>("Date End");
                DaTeEnD.setCellValueFactory(new PropertyValueFactory<>("DateEnd"));

                TableColumn<Ordered, String> ExEcUtOr = new TableColumn<>("Executor");
                ExEcUtOr.setCellValueFactory(new PropertyValueFactory<>("Executor"));

                TableColumn<Ordered, Integer> experienceExecutor = new TableColumn<>("Experience Executor");
                experienceExecutor.setCellValueFactory(new PropertyValueFactory<>("ExperienceExecutor"));

                Order_Table.getColumns().addAll(IDS, MODEL, numbere, dateOfMadee, namee, SURNAME, middleNamee,
                        cost, dateOfIssue1, typeOfWork, dateStart1, DaTeEnD, ExEcUtOr, experienceExecutor);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


        //Кнопка виводу tableView з усіма даними всіх таблиць
        Order_Dop.setOnAction(actionEvent -> {
            DBHANDLER dbHandler = new DBHANDLER();
            ResultSet resultSet;
            try {
                resultSet = dbHandler.Vlasnik();
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
            try {
                resultSet = dbHandler.Auto();
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
            try {
                resultSet = dbHandler.Mech();
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
            try {
                resultSet = dbHandler.Ordered();
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

            TableView tableView = new TableView(observableList);
            System.out.println(observableList.toString());
            tableView.setItems(observableList);

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
            dateBirthday.setCellValueFactory(new PropertyValueFactory<>("Date_Birthday"));

            TableColumn<Auto, Integer> IDS5 = new TableColumn<>("Identy");
            IDS5.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Auto, String> model = new TableColumn<>("Model");
            model.setCellValueFactory(new PropertyValueFactory<>("Model"));

            TableColumn<Auto, String> number = new TableColumn<>("Number");
            number.setCellValueFactory(new PropertyValueFactory<>("Number"));

            TableColumn<Auto, LocalDate> dateOfMade = new TableColumn<>("Date Of Made");
            dateOfMade.setCellValueFactory(new PropertyValueFactory<>("Date_Of_Made"));

            TableColumn<Auto, String> name5 = new TableColumn<>("Name");
            name5.setCellValueFactory(new PropertyValueFactory<>("name_1"));

            TableColumn<Auto, String> surname5 = new TableColumn<>("Surname");
            surname5.setCellValueFactory(new PropertyValueFactory<>("surname_1"));

            TableColumn<Auto, String> middleName5 = new TableColumn<>("MiddleName");
            middleName5.setCellValueFactory(new PropertyValueFactory<>("middleName_1"));


            TableColumn<Mechanic, Integer> IDS4 = new TableColumn<>("Identy");
            IDS4.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Mechanic, String> name4 = new TableColumn<>("Name");
            name4.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn<Mechanic, String> middleName4 = new TableColumn<>("MiddleName");
            middleName4.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

            TableColumn<Mechanic, String> surname4 = new TableColumn<>("Surname");
            surname4.setCellValueFactory(new PropertyValueFactory<>("Surname"));

            TableColumn<Mechanic, Integer> experience = new TableColumn<>("Experience");
            experience.setCellValueFactory(new PropertyValueFactory<>("Experience"));

            TableColumn<Mechanic, String> typeOfWork = new TableColumn<>("Type Of Work");
            typeOfWork.setCellValueFactory(new PropertyValueFactory<>("TypeOfWork"));

            TableColumn<Mechanic, Integer> age = new TableColumn<>("Age");
            age.setCellValueFactory(new PropertyValueFactory<>("Age"));


            TableColumn<Ordered, Integer> IDS3 = new TableColumn<>("Identy");
            IDS3.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Ordered, String> model3 = new TableColumn<>("Model");
            model3.setCellValueFactory(new PropertyValueFactory<>("Model"));

            TableColumn<Ordered, String> number3 = new TableColumn<>("Number");
            number3.setCellValueFactory(new PropertyValueFactory<>("Number"));

            TableColumn<Ordered, LocalDate> dateOfMade3 = new TableColumn<>("Date Of Made");
            dateOfMade3.setCellValueFactory(new PropertyValueFactory<>("DateOfMade"));

            TableColumn<Ordered, String> name3 = new TableColumn<>("Name");
            name3.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn<Ordered, String> surname3 = new TableColumn<>("Surname");
            surname3.setCellValueFactory(new PropertyValueFactory<>("Surname"));

            TableColumn<Ordered, String> middleName3 = new TableColumn<>("MiddleName");
            middleName3.setCellValueFactory(new PropertyValueFactory<>("MiddleName"));

            TableColumn<Ordered, Double> cost = new TableColumn<>("Cost");
            cost.setCellValueFactory(new PropertyValueFactory<>("Cost"));

            TableColumn<Ordered, LocalDate> dateOfIssue = new TableColumn<>("Date Of Issue");
            dateOfIssue.setCellValueFactory(new PropertyValueFactory<>("DateOfIssue"));

            TableColumn<Ordered, String> typeOfWork3 = new TableColumn<>("Type Of Work");
            typeOfWork3.setCellValueFactory(new PropertyValueFactory<>("TypeOfWork"));

            TableColumn<Ordered, LocalDate> dateStart = new TableColumn<>("Date Start");
            dateStart.setCellValueFactory(new PropertyValueFactory<>("DateStart"));

            TableColumn<Ordered, LocalDate> dateEnd = new TableColumn<>("Date End");
            dateEnd.setCellValueFactory(new PropertyValueFactory<>("DateEnd"));

            TableColumn<Ordered, String> executor = new TableColumn<>("Executor");
            executor.setCellValueFactory(new PropertyValueFactory<>("Executor"));

            TableColumn<Ordered, Integer> experienceExecutor = new TableColumn<>("Experience Executor");
            experienceExecutor.setCellValueFactory(new PropertyValueFactory<>("ExperienceExecutor"));

            tableView.getColumns().addAll(IDS3, model3, number3, dateOfMade3, name3, middleName3, surname3,
                    cost, dateOfIssue, typeOfWork3, dateStart, dateEnd, executor, experienceExecutor, model, number, dateOfMade, name5, surname5, middleName5
            , experience, typeOfWork, age, mobilePhone, dateBirthday);

            try {
                dbHandler.Full_SysAdmin();
                // Показать сообщение об успешном создании записи

                Stage warning = new Stage();
                warning.setTitle("Accept!");
                warning.setScene(new Scene(tableView));
                warning.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

            //Кнопка закриття вікна
        WINDOWS_CLOSE.setOnAction(actionEvent -> {
                System.exit(1);
            });
    }

    private  void setAuthorise() {
        ResultSet resultSet;
        DBHANDLER dbQuery = new DBHANDLER();
        observableList = FXCollections.observableArrayList();
        Authorise_Table.getItems().clear();
        Authorise_Table.getColumns().clear();
        try {
            resultSet = dbQuery.Authorise_SysAdmin();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                if (!resultSet.next()) break;
                Authorise authorise = new Authorise(resultSet.getInt("id"),
                        resultSet.getString("Login"),
                        resultSet.getString("PASS"),
                        resultSet.getString("ROLE"));
                observableList.add(authorise);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(observableList.toString());
        Authorise_Table.setItems(observableList);

        TableColumn<Authorise, Integer> IDS = new TableColumn<>("Identy");
        IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Authorise, String> login = new TableColumn<>("login");
        login.setCellValueFactory(new PropertyValueFactory<>("Login"));

        TableColumn<Authorise, String> pass = new TableColumn<>("Password");
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Authorise, String> role = new TableColumn<>("Role");
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));

        Authorise_Table.getColumns().addAll(IDS, login, pass, role);
    }
    private void setOrdered() {
        ResultSet resultSet;
        DBHANDLER dbQuery = new DBHANDLER();
        observableList = FXCollections.observableArrayList();
        Order_Table.getItems().clear();
        Order_Table.getColumns().clear();
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
        System.out.println(observableList.toString());
        Order_Table.setItems(observableList);

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

        Order_Table.getColumns().addAll(IDS, model, number, dateOfMade, name, surname, middleName,
                cost, dateOfIssue, typeOfWork, dateStart, dateEnd, executor, experienceExecutor);
    }

}
