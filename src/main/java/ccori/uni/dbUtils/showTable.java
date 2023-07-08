/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ccori.uni.dbUtils;

/**
 *
 * @author devCcori
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class showTable {

    //Constructor
    private showTable() {
    }

    //define una funcion que obtiene los datos de una tabla y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
    public static javax.swing.table.DefaultTableModel getTableModel(String tableName) throws SQLException {
        javax.swing.table.DefaultTableModel model;
        try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT * FROM " + tableName)
            )
            {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  int numberOfColumns = rsmd.getColumnCount();
                  model = new javax.swing.table.DefaultTableModel();
                  for (int i = 1; i <= numberOfColumns; i++) {
                        model.addColumn(rsmd.getColumnLabel(i).replace("_", " "));
                  }
                  while (rs.next()) {
                        Object[] rowData = new Object[numberOfColumns];
                        for (int i = 0; i < rowData.length; i++) {
                              rowData[i] = rs.getObject(i + 1);
                        }
                        model.addRow(rowData);
                  }
            }
            return model;
      }

    //si tienes una string hola_mundo quiero que devuelva "Hola Mundo":
}
