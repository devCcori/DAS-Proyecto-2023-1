/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ccori.uni.dbUtils;

/**
 *
 * @author devCcori
 */

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class sqlConnection {
    //Constructor
    private sqlConnection () {
    }
    
    public static Connection getConnection() throws SQLException {
        Connection cn = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String urlDB = "jdbc:sqlserver://localhost:1433;databaseName=FastFood;encrypt=true;TrustServerCertificate=True;";
        String user = "tester";
        String pwd = "1234";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance(); //Load Driver
            cn = DriverManager.getConnection(urlDB, user, pwd);//get Connection obj
        } catch (SQLException e) {
            throw e; // propagar la excepcion
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver de la base de datos.");
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new SQLException("No se puede establecer la conexion con la BD.");
        }
        return cn;
    }
}
