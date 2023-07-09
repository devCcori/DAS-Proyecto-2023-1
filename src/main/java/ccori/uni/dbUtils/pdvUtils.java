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
import java.sql.SQLException;
import java.sql.Statement;

public class pdvUtils {

    //constructor
    private pdvUtils() {
    }

    //define una funcion que rellena las opciones de un combobox con la columan de una tabla de una bd sql server:
    public static javax.swing.DefaultComboBoxModel<String> getComboBoxModel(String tableName, String columnName) throws SQLException {
        javax.swing.DefaultComboBoxModel<String> model = new javax.swing.DefaultComboBoxModel<>();
        try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT " + columnName + " FROM " + tableName)
            )
            {
                  while (rs.next()) {
                        model.addElement(rs.getString(columnName));
                  }
            }
            return model;
      }

      //obten la cantidad de filas de la tabla Ventas:
      public static int getRowCount() throws SQLException {
            int rowCount = 0;
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Ventas")
            ) {
                  if (rs.next()) {
                        rowCount = rs.getInt(1);
                  }
            }
            return rowCount;
      }

}