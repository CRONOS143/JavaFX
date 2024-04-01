package com.example.lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CLIENT_WINDOW {

    @FXML
    private TextField ACCEPT_MONEY;

    @FXML
    private TextArea ADDRESS;

    @FXML
    private TextField ANALYTICS_COMPANIA;

    @FXML
    private TableView<Clients> CLIENT;

    @FXML
    private TextField COMPANIA;

    @FXML
    private DatePicker DATE_END;

    @FXML
    private DatePicker DATE_START;

    @FXML
    private TableView<Deposits> DEPOSIT;

    @FXML
    private TextField DOVARENI_COSHTI;

    @FXML
    private TableView<Dovira> DOVIRA;

    @FXML
    private Button DOVIRA_COSHTIV;

    @FXML
    private TextField DOVIRA_NUMBER;

    @FXML
    private TextField IDENT;

    @FXML
    private TextField MOB_PHONE;

    @FXML
    private TextField NAME_VKLADNIK;

    @FXML
    private TableView<quotes> PAPER;

    @FXML
    private Button SEARCHER1;

    @FXML
    private Button SEARCHER2;

    @FXML
    private Button SEARCHER3;

    @FXML
    private Tab TAB_DOVIRA;

    @FXML
    private Tab TAB_INFO;

    @FXML
    private TabPane TAB_PANEL;

    @FXML
    private Tab TAB_VKLAD;

    @FXML
    private Button UPDATER;

    @FXML
    private TextField IDENTO;
    @FXML
    private TextField FOND;
    @FXML
    private TextField VLASNIST;

    private ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        SEARCHER2.setOnAction(actionEvent -> {

            DBQuery dbQuery = new DBQuery();
            try {
                CLIENT.getItems().clear();
                CLIENT.getColumns().clear();
                String number = NAME_VKLADNIK.getText();
                ResultSet resultSet = dbQuery.SearchComp(number);
                CLIENT.getItems().clear(); // очищаем TableView от предыдущих результатов

                // заполняем TableView данными из ResultSet
                ObservableList<Clients> client = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    Clients cliento = new Clients(resultSet.getInt("id"),resultSet.getString("name"),
                            resultSet.getString("ownership_type"),resultSet.getString("address"),
                    resultSet.getString("phone"));
                    client.add(cliento);
                }
                System.out.println(observableList.toString());
                CLIENT.setItems(client);
                TableColumn<Clients, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Clients, String> nameCL = new TableColumn<>("Компанія");
                nameCL.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Clients, String> ownership_type = new TableColumn<>("Власність (вид)");
                ownership_type.setCellValueFactory(new PropertyValueFactory<>("ownership_type"));

                TableColumn<Clients, String> address = new TableColumn<>("Адреса");
                address.setCellValueFactory(new PropertyValueFactory<>("address"));

                TableColumn<Clients, String> phone = new TableColumn<>("Телефон");
                phone.setCellValueFactory(new PropertyValueFactory<>("phone"));


                CLIENT.getColumns().addAll(id, nameCL, ownership_type, address, phone);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        SEARCHER3.setOnAction(actionEvent -> {
            DBQuery dbQuery = new DBQuery();
            try {
                DEPOSIT.getItems().clear();
                DEPOSIT.getColumns().clear();
                String dovira = DOVIRA_NUMBER.getText();
                ResultSet resultSet = dbQuery.SearchDeposit(Integer.parseInt(dovira));
                DEPOSIT.getItems().clear(); // очищаем TableView от предыдущих результатов

                // заполняем TableView данными из ResultSet
                ObservableList<Deposits> deposit = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    Deposits deposito = new Deposits(resultSet.getInt("id"),resultSet.getDouble("amount"),
                            resultSet.getInt("DOVIRA"),resultSet.getDate("date"));
                    deposit.add(deposito);
                }
                System.out.println(observableList.toString());
                DEPOSIT.setItems(deposit);
                TableColumn<Deposits, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<Deposits, Double> amount = new TableColumn<>("Гроші");
                amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

                TableColumn<Deposits, Integer> DOVIRA = new TableColumn<>("Номер довіри");
                DOVIRA.setCellValueFactory(new PropertyValueFactory<>("DOVIRA"));

                TableColumn<Deposits, Date> date = new TableColumn<>("Дата депозиту");
                date.setCellValueFactory(new PropertyValueFactory<>("date"));

                DEPOSIT.getColumns().addAll(id, amount, DOVIRA, date);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                PAPER.getItems().clear();
                PAPER.getColumns().clear();
                String dovira = DOVIRA_NUMBER.getText();
                ResultSet resultSet = dbQuery.SearchPaper(Integer.parseInt(dovira));
                PAPER.getItems().clear(); // очищаем TableView от предыдущих результатов

                // заполняем TableView данными из ResultSet
                ObservableList<quotes> quo = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    quotes quot = new quotes(resultSet.getInt("id"),resultSet.getString("security_name"),
                            resultSet.getDouble("price"),resultSet.getDate("date"),resultSet.getInt("Dovira"));
                    quo.add(quot);
                }
                System.out.println(observableList.toString());
                PAPER.setItems(quo);
                TableColumn<quotes, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn<quotes, String> security_name = new TableColumn<>("Назва паперів");
                security_name.setCellValueFactory(new PropertyValueFactory<>("security_name"));

                TableColumn<quotes, Double> price = new TableColumn<>("Ціна");
                price.setCellValueFactory(new PropertyValueFactory<>("price"));

                TableColumn<quotes, Date> date = new TableColumn<>("Дата депозиту");
                date.setCellValueFactory(new PropertyValueFactory<>("date"));

                TableColumn<quotes, Integer> DOVIRA = new TableColumn<>("Номер довіри");
                DOVIRA.setCellValueFactory(new PropertyValueFactory<>("DOVIRA"));


                PAPER.getColumns().addAll(id,security_name, price, date, DOVIRA);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        UPDATER.setOnAction(actionEvent -> {
            DBQuery dbQuery = new DBQuery();
            try {
                int id = Integer.parseInt(IDENT.getText());
                String name = NAME_VKLADNIK.getText();
                String vlasn = VLASNIST.getText();
                String addres = ADDRESS.getText();
                String phone = MOB_PHONE.getText();
                dbQuery.UPDCLient(vlasn, addres, phone, id,name);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        SEARCHER1.setOnAction(actionEvent -> {

            DBQuery dbQuery = new DBQuery();
            try {
                DOVIRA.getItems().clear();
                DOVIRA.getColumns().clear();
                String name = COMPANIA.getText();
                ResultSet resultSet = dbQuery.SearchDovirennist(name);
                DOVIRA.getItems().clear(); // очищаем TableView от предыдущих результатов

                // заполняем TableView данными из ResultSet
                ObservableList<Dovira> doviras = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    Dovira doviro = new Dovira(resultSet.getInt("id"),resultSet.getDate("term_start"),
                            resultSet.getDate("term_end"),resultSet.getString("Fund"),
                            resultSet.getString("name"),resultSet.getDouble("investment_amount"),
                            resultSet.getString("COMP_NAME"),resultSet.getDouble("investments_return"));
                    doviras.add(doviro);
                }
                System.out.println(observableList.toString());
                DOVIRA.setItems(doviras);
                TableColumn<Dovira, Integer> id = new TableColumn<>("Ідентифікатор");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

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

                DOVIRA.getColumns().addAll(id, term_start, term_end, Fund, Names, investment_amount, COMP_NAME, investments_return);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        DOVIRA_COSHTIV.setOnAction(actionEvent -> {
            DBQuery dbQuery = new DBQuery();
            try {
                int id = Integer.parseInt(IDENTO.getText());
                Date start = Date.valueOf(DATE_START.getValue());
                Date end = Date.valueOf(DATE_END.getValue());
                String fond = FOND.getText();
                String compania = COMPANIA.getText();
                double money = Double.parseDouble(DOVARENI_COSHTI.getText());
                String analyticsCompaniaText = ANALYTICS_COMPANIA.getText();
                double accept_moneyText = Double.parseDouble(ACCEPT_MONEY.getText());

                dbQuery.ADDDovira(id,start, end, fond, compania, money, analyticsCompaniaText, accept_moneyText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}