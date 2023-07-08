/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.db;

/**
 *
 * @author devCcori
 */


import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ccori.uni.dbUtils.sqlConnection;

public class dbConnection {
    
    public static void main(String[] args) {
        //crea un try and catch que pruebe la conneccion a la base de datos:
        try(Connection cn = sqlConnection.getConnection()){
            System.out.println("EUREKAAAA!!!!");
            cn.close();
        }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
        }
    }
}