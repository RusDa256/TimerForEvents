/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javalab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Андрей
 */
public class DataBase {
    Connection connection;
    
    public void connect() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\tmp\\titles.db");
            System.out.println("Driver connected");
            System.out.println("Connection to DB");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertRecToDb(int id, String time, String title) {
        String insert = "INSERT INTO Records (id_user, time, title) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.setString(2, time);
            stmt.setString(3, title);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<MyPair> getAllPair() {
        
        ArrayList<MyPair> allPair = new ArrayList<>();
        String time = "";
        String req = "SELECT * FROM Records";
        try{
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(req);
            while(res.next()) {
                MyPair tmp = new MyPair(res.getString("time"), res.getString("title"));
                allPair.add(tmp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allPair;
    }
    
    DataBase()
    {
        connect();
    }
}
