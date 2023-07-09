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
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
//crea una clase llamada transactions
public class transactions{
    //constructor
    private transactions(){
    }

    //define una funcion que suba datos de una jtable a una tabla de una base de datos sql server:
    public static void uploadCompra(JTable table, String tableName) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "INSERT INTO " + tableName + " VALUES (";
            for (int i = 0; i < table.getColumnCount(); i++) {
                sql += "?,";
            }
            sql = sql.substring(0, sql.length() - 1) + ")";
            ps = cn.prepareStatement(sql);
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    ps.setObject(j + 1, table.getValueAt(i, j));
                }
                ps.addBatch();
            }
            ps.executeBatch();
            cn.commit();
            JOptionPane.showMessageDialog(null, "Datos subidos correctamente.");
        } catch (SQLException e) {
            cn.rollback();
            throw e;
        } finally {
            ps.close();
            cn.close();
        }
    }


}