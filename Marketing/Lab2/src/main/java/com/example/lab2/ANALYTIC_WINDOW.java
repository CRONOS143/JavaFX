package com.example.lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ANALYTIC_WINDOW {

    @FXML
    private Button ADD;

    @FXML
    private Button ADDD;

    @FXML
    private TableView<analytics> ANALYTICS;

    @FXML
    private TableView<Clients> CLIENT;

    @FXML
    private TextField CLIENTOS;

    @FXML
    private TextField COMPANY_NAME;

    @FXML
    private TextField COST;

    @FXML
    private DatePicker DATEEE;

    @FXML
    private DatePicker DATE_DEPOSIT;

    @FXML
    private TableView<Deposits> DEPOSIT;

    @FXML
    private TableView<Dovira> DOVIRAAA;

    @FXML
    private TextField IDENT;

    @FXML
    private TextField IDENTI;

    @FXML
    private TextField IDENTITIT;

    @FXML
    private TextField IDENTO;

    @FXML
    private TextField MONEYS;

    @FXML
    private TextField NAME_PAPER;

    @FXML
    private TextField NAME_PAPERS;

    @FXML
    private TextField NUMBER_DOV;

    @FXML
    private TableView<securities> PAPER;

    @FXML
    private TableView<quotes> QUOTE;

    @FXML
    private Button SEARCHER1;

    @FXML
    private Button SEARCHER2;

    @FXML
    private TextField TYPE_PAPER;

    @FXML
    private Button UPDATE;

    @FXML
    private TextField VKLAD_COST;

    @FXML
    private TextField VKLAD_MONEY;

    @FXML
    private Tab INVESTMENT;

    @FXML
    private Tab CLIETN;

    @FXML
    private Tab CABINET;

    @FXML
    private TabPane ADMINISTRATOR;

    private ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        ADMINISTRATOR.setOnMouseClicked(mouseEvent -> {

            ResultSet resultSet;
            DBQuery dbQuery = new DBQuery();
            if (INVESTMENT.isSelected()) {
            //Котирування
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                QUOTE.getItems().clear();
                QUOTE.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewQuote();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        quotes quot = new quotes(resultSet.getInt("id"),resultSet.getString("security_name"),
                                resultSet.getDouble("price"),resultSet.getDate("date"),resultSet.getInt("DOVIRA"));
                        observableList.add(quot);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                QUOTE.setItems(observableList);

                TableColumn<quotes, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<quotes, String> security_name = new TableColumn<>("Назва паперів");
                security_name.setCellValueFactory(new PropertyValueFactory<>("security_name"));

                TableColumn<quotes, Double> price = new TableColumn<>("Ціна");
                price.setCellValueFactory(new PropertyValueFactory<>("price"));

                TableColumn<quotes, Date> date = new TableColumn<>("Дата вкладу");
                date.setCellValueFactory(new PropertyValueFactory<>("date"));

                TableColumn<quotes, Integer> DOVIRA = new TableColumn<>("№ Довіри");
                DOVIRA.setCellValueFactory(new PropertyValueFactory<>("DOVIRA"));


                QUOTE.getColumns().addAll(id, security_name, price, date, DOVIRA);

                //Депозити
                observableList = FXCollections.observableArrayList();
                DEPOSIT.getItems().clear();
                DEPOSIT.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewDeposit();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Deposits deposito = new Deposits(resultSet.getInt("id"),resultSet.getDouble("amount"),
                                resultSet.getInt("DOVIRA"),resultSet.getDate("date"));
                        observableList.add(deposito);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                DEPOSIT.setItems(observableList);

                TableColumn<Deposits, Integer> ido = new TableColumn<>("Ідентифікатор");
                ido.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Deposits, Double> amount = new TableColumn<>("Гроші");
                amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

                TableColumn<Deposits, Integer> DOVIRAOS = new TableColumn<>("Номер довіри");
                DOVIRAOS.setCellValueFactory(new PropertyValueFactory<>("DOVIRA"));

                TableColumn<Deposits, Date> dateo = new TableColumn<>("Дата депозиту");
                dateo.setCellValueFactory(new PropertyValueFactory<>("date"));


                DEPOSIT.getColumns().addAll(ido, amount, DOVIRAOS, dateo);

                //Цінні папери
                observableList = FXCollections.observableArrayList();
                PAPER.getItems().clear();
                PAPER.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewPaper();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        securities sec = new securities(resultSet.getInt("id"),resultSet.getString("name"),
                                resultSet.getString("type"));
                        observableList.add(sec);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                PAPER.setItems(observableList);

                TableColumn<securities, Integer> IDS = new TableColumn<>("Ідентифікатор");
                IDS.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<securities, String> names = new TableColumn<>("Назва");
                names.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<securities, String> type = new TableColumn<>("Вид паперів");
                type.setCellValueFactory(new PropertyValueFactory<>("type"));

                PAPER.getColumns().addAll(IDS, names, type);


            }

            if (CLIETN.isSelected()) {
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                CLIENT.getItems().clear();
                CLIENT.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewClient();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Clients client = new Clients(resultSet.getInt("id"),resultSet.getString("name"),
                                resultSet.getString("ownership_type"),resultSet.getString("address"),resultSet.getString("phone"));
                        observableList.add(client);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                CLIENT.setItems(observableList);

                TableColumn<Clients, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Clients, String> name = new TableColumn<>("Назва підприємства-клієнта");
                name.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Clients, String> ownership_type = new TableColumn<>("Власність");
                ownership_type.setCellValueFactory(new PropertyValueFactory<>("ownership_type"));

                TableColumn<Clients, String> address = new TableColumn<>("Адреса");
                address.setCellValueFactory(new PropertyValueFactory<>("address"));

                TableColumn<Clients, String> phone = new TableColumn<>("Мобільний телефон");
                phone.setCellValueFactory(new PropertyValueFactory<>("phone"));


                CLIENT.getColumns().addAll(id, name, ownership_type, address, phone);


                //Довіри
                observableList = FXCollections.observableArrayList();
                DOVIRAAA.getItems().clear();
                DOVIRAAA.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewDoviras();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        Dovira doviro = new Dovira(resultSet.getInt("id"),resultSet.getDate("term_start"),
                                resultSet.getDate("term_end"),resultSet.getString("Fund"),
                                resultSet.getString("name"),resultSet.getDouble("investment_amount"),
                                resultSet.getString("COMP_NAME"),resultSet.getDouble("investments_return"));
                        observableList.add(doviro);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                DOVIRAAA.setItems(observableList);

                TableColumn<Dovira, Integer> ido = new TableColumn<>("Ідентифікатор");
                ido.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Dovira, Date> term_start = new TableColumn<>("Дата укладання довіри");
                term_start.setCellValueFactory(new PropertyValueFactory<>("term_start"));

                TableColumn<Dovira, Date> term_end = new TableColumn<>("Дата закінчення довіри");
                term_end.setCellValueFactory(new PropertyValueFactory<>("term_end"));

                TableColumn<Dovira, String> Fund = new TableColumn<>("Фонд");
                Fund.setCellValueFactory(new PropertyValueFactory<>("Fund"));

                TableColumn<Dovira, String> Names = new TableColumn<>("Вкладник");
                Names.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Dovira, Double> investment_amount = new TableColumn<>("Довірені кошти");
                investment_amount.setCellValueFactory(new PropertyValueFactory<>("investment_amount"));

                TableColumn<Dovira, String> COMP_NAME = new TableColumn<>("Аналітична компанія");
                COMP_NAME.setCellValueFactory(new PropertyValueFactory<>("COMP_NAME"));

                TableColumn<Dovira, Double> investments_return = new TableColumn<>("Вкладені гроші, сумарно");
                investments_return.setCellValueFactory(new PropertyValueFactory<>("investments_return"));


                DOVIRAAA.getColumns().addAll(ido, term_start, term_end, Fund, Names, investment_amount, COMP_NAME, investments_return);
            }


            if (CABINET.isSelected()) {
                observableList.clear();
                observableList = FXCollections.observableArrayList();
                ANALYTICS.getItems().clear();
                ANALYTICS.getColumns().clear();
                try {
                    resultSet = dbQuery.ViewAnalytics();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        analytics analytic = new analytics(resultSet.getInt("ID"), resultSet.getString("COMP_NAME"),
                                resultSet.getInt("CLIENTS"),resultSet.getDouble("MONEY"),resultSet.getDouble("investments_return"));
                        observableList.add(analytic);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(observableList.toString());
                ANALYTICS.setItems(observableList);
                TableColumn<analytics, Integer> ID = new TableColumn<>("Ідентифікатор");
                ID.setCellValueFactory(new PropertyValueFactory<>("ID"));

                TableColumn<analytics, String> COMP_NAME = new TableColumn<>("Назва");
                COMP_NAME.setCellValueFactory(new PropertyValueFactory<>("COMP_NAME"));

                TableColumn<analytics, Integer> CLIENTS = new TableColumn<>("Кількість клієнтів");
                CLIENTS.setCellValueFactory(new PropertyValueFactory<>("CLIENTS"));

                TableColumn<analytics, Double> MONEY = new TableColumn<>("Гроші компанії");
                MONEY.setCellValueFactory(new PropertyValueFactory<>("MONEY"));

                TableColumn<analytics, Double> investments_return = new TableColumn<>("Адреса");
                investments_return.setCellValueFactory(new PropertyValueFactory<>("investments_return"));


                ANALYTICS.getColumns().addAll(ID, COMP_NAME, CLIENTS, MONEY, investments_return);

            }

        });
   }
}
