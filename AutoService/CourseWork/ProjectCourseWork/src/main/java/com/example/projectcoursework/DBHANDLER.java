package com.example.projectcoursework;

import java.sql.*;

public class DBHANDLER extends DBCONNECT {
    Connection dbConnection;
    private PreparedStatement preparedStatement;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    //Перевірка даних авторизації
    public ResultSet Authorise(String login, String pass) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.authorise WHERE LOGIN = '"+login+"' AND PASS = '"+pass+"';";
        return st.executeQuery(select);
    }


    /*  ДЛЯ ВІКНА АДМІНІСТРАТОРА для РЕЄСТРАЦІЇ ЗАЯВОК ТА КЛІЄНТІВ */
    //Вивід даних про власників
    public ResultSet Vlasnik() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.vlasnik;";
        return st.executeQuery(select);
    }
    // Вивід даних про механиків
    public ResultSet Mech() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.mechanic;";
        return st.executeQuery(select);
    }
    // Вивід даних про заявки
    public ResultSet Ordered() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.ordered;";
        return st.executeQuery(select);
    }
    //Вивід даних про автівки власників
    public ResultSet Auto() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.auto;";
        return st.executeQuery(select);
    }


    // Додаю данні про клієнта
    public void AddVlasn(String name, String surname, String middleName,int phone, Date date_birthday) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `my_autoservice`.`VLASNIK` (`NAME`, `SURNAME`, `MIDDLENAME`, `MOBILE_PHONE`, `DATE_BIRTHDAY`) VALUES  \n" +
                "(?, ?, ?, ?, ?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, middleName);
        statement.setInt(4, phone);
        statement.setDate(5, date_birthday);
        statement.executeUpdate();
    }
    //Додаю данні про автівку клієнта
    public void AddAuto(String Model, String Number, Date DateOfMade, String name, String MiddleName, String Surname) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `my_autoservice`.`AUTO` (`MODEL`, `NUMBER`, `DATE_OF_MADE`, `NAME_1`, `SURNAME_1`, `MIDDLENAME_1`)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, Model);
        statement.setString(2, Number);
        statement.setDate(3, DateOfMade);
        statement.setString(4, name);
        statement.setString(5, MiddleName);
        statement.setString(6, Surname);
        statement.executeUpdate();
    }
    //Додаю данні про заявки
    public void AddOrder(String model, String number, Date DateOfMade, String name, String Surname, String MiddleName, Double Cost, String DateOfIssue, String TypeOfWork, String DateStart, String DateEnd, String Executor, int ExpExecutor) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `my_autoservice`.`ORDERED` (`MODEL`, `NUMBER`, `DATE_OF_MADE`, `NAME`, `SURNAME`, `MIDDLENAME`, `COST`, `DATE_OF_ISSUE`, `TYPE_WORK`, `DATE_START`, `DATE_END`, `EXECUTOR`, `EXPERIENCE_EXECUTOR`)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, model);
        statement.setString(2, number);
        statement.setDate(3, DateOfMade);
        statement.setString(4, name);
        statement.setString(5, Surname);
        statement.setString(6, MiddleName);
        statement.setDouble(7, Cost);
        statement.setString(8, DateOfIssue);
        statement.setString(9, TypeOfWork);
        statement.setString(10, DateStart);
        statement.setString(11, DateEnd);
        statement.setString(12, Executor);
        statement.setInt(13, ExpExecutor);
        statement.executeUpdate();
    }
    //Додаю данні про Механиків
    public void AddMech(String name, String middlename, String Surname, int exp, String Special, int age) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `my_autoservice`.`MECHANIC` (`NAME`, `MIDDLENAME`, `SURNAME`, `EXPERIENCE`, `TYPE_WORK`, `AGE`)\n" +
                "VALUES \n" +
                "(?,?,?,?,?,?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, middlename);
        statement.setString(3, Surname);
        statement.setInt(4, exp);
        statement.setString(5, Special);
        statement.setInt(6, age);
        statement.executeUpdate();
    }
    //Видаляю данні про клієнта
    public void DelVlasn(int Id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM `my_autoservice`.`vlasnik` WHERE (`ID` = ?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, Id);
        statement.executeUpdate();
    }
    //Видаляю данні про автівку клієнта
    public void DelAuto(int Id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM `my_autoservice`.`auto` WHERE (`ID` = ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, Id);
        statement.executeUpdate();
    }
    //Видаляю данні про заявки
    public void DelOrder(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM `my_autoservice`.`ordered` WHERE (`ID` = ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    //Видаляю данні про Механиків
    public void DelMech(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM `my_autoservice`.`mechanic` WHERE (`ID` = ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    //Оновлюю данні про клієнта
    public void UpdVlasn(String name, String surname, String middleName,int phone, Date date_birthday, int Id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `my_autoservice`.`vlasnik` SET `NAME` = ?, `SURNAME` = ?, `MIDDLENAME` = ?, `MOBILE_PHONE` = ?, `DATE_BIRTHDAY` = ? WHERE (`ID` = ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, surname);
        statement.setString(3, middleName);
        statement.setInt(4, phone);
        statement.setDate(5, date_birthday);
        statement.setInt(6,Id);
        statement.executeUpdate();
    }
    //Оновлюю данні про автівку клієнта

    public void UpdAuto(String model, String number, Date DateOfMade, int Id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `my_autoservice`.`auto` SET `MODEL` = ?, `NUMBER` = ?, `DATE_OF_MADE` = ? WHERE (`ID` = ?);\n";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, model);
        statement.setString(2, number);
        statement.setDate(3, DateOfMade);
        statement.setInt(4,Id);
        statement.executeUpdate();

    }
    //Оновлюю данні про заявки
    public void UpdOrder(String surname, Date dateOfIssue, String TypeWork, Date DateStart, Date DateEnd, String EXECUTOR, int EXPERIENCE_EXECUTOR, int Id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `my_autoservice`.`ordered` SET `SURNAME` = ?, `DATE_OF_ISSUE` = ?, `TYPE_WORK` = ?, `DATE_START` = ?, `DATE_END` = ?, `EXECUTOR` = ?, `EXPERIENCE_EXECUTOR` = ? WHERE (`ID` = ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, surname);
        statement.setDate(2, dateOfIssue);
        statement.setString(3, TypeWork);
        statement.setDate(4, DateStart);
        statement.setDate(5, DateEnd);
        statement.setString(6, EXECUTOR);
        statement.setInt(7,EXPERIENCE_EXECUTOR);
        statement.setInt(8,Id);
        statement.executeUpdate();
    }
    //Оновлюю данні про Механиків
    public void UpdMech(String name, String MiddleName, String Surname, int EXPERIENCE, String TypeOfWork, int Id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE `my_autoservice`.`mechanic` SET `NAME` = ?, `MIDDLENAME` = ?, `SURNAME` = ?, `EXPERIENCE` = ?, `TYPE_WORK` = ?, `AGE` = ? WHERE (`ID` = ?);";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, MiddleName);
        statement.setString(3, Surname);
        statement.setInt(4, EXPERIENCE);
        statement.setString(5, TypeOfWork);
        statement.setInt(6,Id);
        statement.executeUpdate();
    }
    //Шукаю клієнта
    public ResultSet SearchClient(String id, String surname, String phone, Date dateOfBirthday) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.vlasnik WHERE ID = '"+id+"' OR SURNAME = '"+surname+"' OR MOBILE_PHONE = '"+phone+"' OR DATE_BIRTHDAY = '"+dateOfBirthday+"';";
        return st.executeQuery(select);
    }
    //Шукаю автівку клієнта
    public ResultSet SearchAuto(String id, String model, String number, String surname) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.auto WHERE ID = '"+id+"' OR MODEL = '"+model+"' OR NUMBER = '"+number+"' OR SURNAME_1 = '"+surname+"';";
        return st.executeQuery(select);
    }
    //Шукаю заявки
    public ResultSet SearcOrder(String id, String model, String Surname, Date dateOfIssue, Date DateStart, Date DateEnd, String EXECUTOR) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.ordered WHERE ID = '"+id+"' OR MODEL = '"+model+"' OR SURNAME = '"+Surname+"' OR DATE_OF_ISSUE = '"+dateOfIssue+"' OR DATE_START = '"+DateStart+"' OR DATE_END = '"+DateEnd+"' OR EXECUTOR = '"+EXECUTOR+"';";
        return st.executeQuery(select);
    }
    //Шукаю Механиків
    public ResultSet SearchMech(String id, String surname, String type_work, String age) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.mechanic WHERE ID = '"+id+"' OR SURNAME = '"+surname+"' OR TYPE_WORK = '"+type_work+"' OR AGE = '"+age+"';";
        return st.executeQuery(select);
    }




    /* ВІКНО СИСТЕМНОГО АДМІНІСТРАТОРА! */

    //Вивід даних:
    //Авторизації:
    public ResultSet Authorise_SysAdmin() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.authorise;";
        return st.executeQuery(select);
    }
    //Заявки
    public ResultSet Ordered_SysAdmin() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT * FROM my_autoservice.ordered;";
        return st.executeQuery(select);
    }

    //Повна інформація з усіх таблиць:
    public ResultSet Full_SysAdmin() throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT \n" +
                "    v.NAME AS Owner_Name,\n" +
                "    v.SURNAME AS Owner_Surname,\n" +
                "    v.MIDDLENAME AS Owner_Middlename,\n" +
                "    v.MOBILE_PHONE AS Owner_Mobile_Phone,\n" +
                "    v.DATE_BIRTHDAY AS Owner_Date_Birthday,\n" +
                "    a.MODEL AS Car_Model,\n" +
                "    a.NUMBER AS Car_Number,\n" +
                "    a.DATE_OF_MADE AS Car_Date_Made,\n" +
                "    m.NAME AS Mechanic_Name,\n" +
                "    m.SURNAME AS Mechanic_Surname,\n" +
                "    m.MIDDLENAME AS Mechanic_Middlename,\n" +
                "    m.EXPERIENCE AS Mechanic_Experience,\n" +
                "    m.TYPE_WORK AS Mechanic_Type_Work,\n" +
                "    m.AGE AS Mechanic_Age,\n" +
                "    o.COST AS Order_Cost,\n" +
                "    o.DATE_OF_ISSUE AS Order_Date_Issue,\n" +
                "    o.TYPE_WORK AS Order_Type_Work,\n" +
                "    o.DATE_START AS Order_Date_Start,\n" +
                "    o.DATE_END AS Order_Date_End,\n" +
                "    o.EXECUTOR AS Order_Executor,\n" +
                "    o.EXPERIENCE_EXECUTOR AS Order_Executor_Experience\n" +
                "FROM \n" +
                "    VLASNIK v\n" +
                "JOIN \n" +
                "    AUTO a ON v.NAME = a.NAME_1 AND v.SURNAME = a.SURNAME_1 AND v.MIDDLENAME = a.MIDDLENAME_1\n" +
                "JOIN \n" +
                "    ORDERED o ON v.NAME = o.NAME AND v.SURNAME = o.SURNAME AND v.MIDDLENAME = o.MIDDLENAME\n" +
                "JOIN \n" +
                "    MECHANIC m ON o.EXECUTOR = m.SURNAME;\n";
        return st.executeQuery(select);
    }
    //Робота з обліковими записами:

    //Додаю:
    public void addAuthorise(String login, String pass, String role) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO AUTHORISE (LOGIN, PASS, ROLE) VALUES (?, ?, ?)";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, pass);
        statement.setString(3, role);
        statement.executeUpdate();
    }
    //Пошук:
    public ResultSet searchAuthorise(String id, String Login, String Pass, String Role) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM AUTHORISE WHERE ID LIKE ? OR LOGIN LIKE ? OR PASS LIKE ? OR ROLE LIKE ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, Login);
        statement.setString(3, Pass);
        statement.setString(4, Role);
        return statement.executeQuery();
    }

    //Видаляю:
    public void deleteAuthorise(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM AUTHORISE WHERE ID = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    //Оновлюю:
    public void updateAuthorise(String login, String pass, String role, int id) throws SQLException, ClassNotFoundException {
        String query = "UPDATE AUTHORISE SET LOGIN = ?, PASS = ?, ROLE = ? WHERE ID = ?";
        PreparedStatement statement = getDBConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, pass);
        statement.setString(3, role);
        statement.setInt(4, id);
        statement.executeUpdate();
    }


    //Пошук ордерів по ВСІМ даним
    public ResultSet SearcOrder_SysAdmin(String id, String model, String number,
                                         Date dateOfMade, String name, String surname,
                                         String middleName, String Cost,
                                         Date dateOfIssue, String Type_Work, Date DateStart,
                                         Date DateEnd, String EXECUTOR, String ExpExecutor) throws SQLException,ClassNotFoundException{
        Statement st = getDBConnection().createStatement();
        String select = "SELECT *\n" +
                "FROM ORDERED\n" +
                "WHERE \n" +
                "    ID  = '"+id+"'\n"+
                "    OR MODEL LIKE '"+model+"'\n" +
                "    OR NUMBER LIKE '"+number+"'\n" +
                "    OR DATE_OF_MADE = '"+dateOfMade+"'\n" +
                "    OR NAME LIKE '"+name+"'\n" +
                "    OR SURNAME LIKE '"+surname+"'\n" +
                "    OR MIDDLENAME LIKE '"+middleName+"'\n" +
                "    OR COST = '"+Cost+"'\n" +
                "    OR DATE_OF_ISSUE = '"+dateOfIssue+"'\n" +
                "    OR TYPE_WORK LIKE '"+Type_Work+"'\n" +
                "    OR DATE_START = '"+DateStart+"'\n" +
                "    OR DATE_END = '"+DateEnd+"'\n" +
                "    OR EXECUTOR LIKE '"+EXECUTOR+"'\n" +
                "    OR EXPERIENCE_EXECUTOR = '"+ExpExecutor+"';";
        return st.executeQuery(select);
    }


}
