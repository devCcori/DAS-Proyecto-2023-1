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

      //define una funcion que obtiene los datos de una tabla y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
      public static javax.swing.table.DefaultTableModel getTopModel(String tableName) throws SQLException {
        javax.swing.table.DefaultTableModel model;
        try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT TOP 50 * FROM " + tableName + " WHERE Estado != 'Entregado' ORDER BY ID_Venta DESC")
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

      //defina un funcionn que obtiene los datos de una tabla pertenecientes al ultimo mes segun la columna timestamp y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
      public static javax.swing.table.DefaultTableModel getTableModelLastMonth(String tableName) throws SQLException {
            javax.swing.table.DefaultTableModel model;
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE Fecha >= DATEADD(MONTH, -1, GETDATE())")
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

      //defina un funcionn que obtiene los datos de una tabla pertenecientes al ultimo año segun la columna timestamp y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
      public static javax.swing.table.DefaultTableModel getTableModelLastYear(String tableName) throws SQLException {
            javax.swing.table.DefaultTableModel model;
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE Fecha >= DATEADD(YEAR, -1, GETDATE())")
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

      //define una funcion que obtiene los datos de una tabla y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
      public static javax.swing.table.DefaultTableModel getTableModelV(String tableName) throws SQLException {
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

      //defina un funcionn que obtiene los datos de una tabla pertenecientes al ultimo mes segun la columna timestamp y los nombres de las columnas de la misma tabla y devuelve un TableModel, usando la funcion getConnection del archivo sqlConnection.java, y usando un bd sql server:
      public static javax.swing.table.DefaultTableModel getTableModelLastMonthV(String tableName) throws SQLException {
            javax.swing.table.DefaultTableModel model;
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE TimeStamp >= DATEADD(MONTH, -1, GETDATE())")
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


      public static javax.swing.table.DefaultTableModel getTableModelLastYearV(String tableName) throws SQLException {
            javax.swing.table.DefaultTableModel model;
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
                  ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE TimeStamp >= DATEADD(YEAR, -1, GETDATE())")
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

      //define una funcion que obtiene los totales (que estan en la ultima columna de una Jtable) y los suma, y devuelve el resultado, usando la funcion getTableModel del archivo showTable.java:
      public static double getTotal(javax.swing.JTable table, int column) {
            double total = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                  total += Double.parseDouble(table.getValueAt(i, column).toString());
            }
            return total;
      }

      //define un funcion que actualiza un valor de una toabla, dandole el numero de columna (empezando de 0) y tambien el numero de fila (empezando de 0), de la tabla Productos:
      public static void updateValue(String tableName, String columnName, String value, String id) throws SQLException {
            try (
                  Connection cn = sqlConnection.getConnection();
                  Statement st = cn.createStatement();
            )
            {
                  st.executeUpdate("UPDATE " + tableName + " SET " + columnName + " = '" + value + "' WHERE ID_Producto = '" + id + "'");
            }
      }
}
