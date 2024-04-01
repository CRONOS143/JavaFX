package com.example.lab2;

import java.sql.*;

public class DBQuery extends DataBase{
    Connection dbConnection;
    private PreparedStatement preparedStatement;


    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";;
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    //Перевірка даних авторизації
    public ResultSet Authorise(String login, String pass) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "Select * FROM user\n" +
                "Where username = '"+login+"' AND password = '"+pass+"';";
        return st.executeQuery(select);
    }
    //Відновлення даних
    public void Reset(String login, String password, String role) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `investments`.`user` SET `password` = ?, `role` = ? WHERE (`username` = ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, password);
        statement.setString(2, role);
        statement.setString(3, login);
        statement.executeUpdate();
    }
    //Регістрація користувача системи
    public void REG(String login, String password, String role) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `investments`.`user` (`username`, `password`, `role`) VALUES (?, ?, ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3, role);
        statement.executeUpdate();
    }
    public void ADDCLient(String name, String ownership_type, String address, String phone) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `investments`.`clients` (`name`, `ownership_type`, `address`, `phone`) VALUES (?, ?, ?, ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, ownership_type);
        statement.setString(3, address);
        statement.setString(4, phone);
        statement.executeUpdate();
    }
    //Пошук компанії
    public ResultSet SearchComp(String name) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM clients WHERE name = '"+name+"';";
        return st.executeQuery(select);
    }
    //Пошук депозитів
    public ResultSet SearchDeposit(int number) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM deposits WHERE DOVIRA = '"+number+"';";
        return st.executeQuery(select);
    }
    //Пошук паперів
    public ResultSet SearchPaper(int number) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM quotes WHERE DOVIRA = '"+number+"';";
        return st.executeQuery(select);
    }
    //Оновлення інформації про компанію
    public void UPDCLient(String ownership_type, String address, String phone, int id, String name) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `investments`.`clients` SET `ownership_type` = ?, `address` = ?, `phone` = ? WHERE (`id` = ?) and (`name` = ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, ownership_type);
        statement.setString(2, address);
        statement.setString(3, phone);
        statement.setInt(4, id);
        statement.setString(5, name);
        statement.executeUpdate();
    }
    //Пошук компанії довіри
    public ResultSet SearchDovirennist(String name) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM dovira WHERE name = '"+name+"';";
        return st.executeQuery(select);
    }
    //Довіра коштів аналітичному центру
    public void ADDDovira(int id, Date term_start, Date term_end, String Fund, String name, Double investment_amount, String COMP_NAME, Double investments_return) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `investments`.`dovira` (`id`,`term_start`, `term_end`, `Fund`, `name`, `investment_amount`, `COMP_NAME`, `investments_return`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.setDate(2, term_start);
        statement.setDate(3, term_end);
        statement.setString(4, Fund);
        statement.setString(5, name);
        statement.setDouble(6, investment_amount);
        statement.setString(7, COMP_NAME);
        statement.setDouble(8, investments_return);
        statement.executeUpdate();
    }
    //Вивід всіх котирувань
    public ResultSet ViewQuote() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.quotes;";
        return st.executeQuery(select);
    }
    //Вивід всіх депозитів
    public ResultSet ViewDeposit() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.deposits;";
        return st.executeQuery(select);
    }
    //Вивід всіх паперів
    public ResultSet ViewPaper() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.securities;";
        return st.executeQuery(select);
    }
    //Вивід всіх клієнтів
    public ResultSet ViewClient() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.clients;";
        return st.executeQuery(select);
    }
    //Вивід всіх довір від клієнтів
    public ResultSet ViewDoviras() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.dovira;";
        return st.executeQuery(select);
    }
    //Вивід всіх компаній аналітичного центру
    public ResultSet ViewAnalytics() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM investments.analytics;";
        return st.executeQuery(select);
    }
}

