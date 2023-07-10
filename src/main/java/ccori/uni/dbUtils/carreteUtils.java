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

public class carreteUtils {
    //constructor
    private carreteUtils(){
    }

    //define una funcion que actualiza el valor de estado en la tabla Carrete Pedidos, segun su ID_Venta:
    public static void updateEstado(String idVenta, String estado) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "UPDATE Carrete_Pedidos SET Estado = ? WHERE ID_Venta = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setString(2, idVenta);
            ps.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            if (cn != null) {
                cn.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }
}