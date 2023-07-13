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
import java.sql.ResultSet;
import java.sql.SQLException;

public class compraUtils {
    
    //constructor
    private compraUtils(){
    }

    //define una funcion que robtiene el unico valor de una consulta sql y lo guarda en un string:
    public static String getValue(String sql) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        String value = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getString(1);
                if (value == null) {
                    value = "";
                }
            }
            return value;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    //define un funcion para insertar datos a la tabla Compra_Total la cual necesita de Fecha(Actual con hora y minuto),Cod_Compra,Total,Id_Proveedor:
    public static void insertCompraTotal(String codCompra, String total, String idProveedor, String idEmpleado) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "INSERT INTO Compra_Total VALUES (GETDATE(),?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, codCompra);
            ps.setString(2, total);
            ps.setString(3, idProveedor);
            ps.setString(4, idEmpleado);
            ps.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
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

    //actualiza el valor del stock agregando la cantidad de productos comprados:
    public static void updateStock(String codProducto, String cantidad) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "UPDATE Producto SET Stock = Stock + ? WHERE Cod_Producto = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, cantidad);
            ps.setString(2, codProducto);
            ps.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
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

    //actualiza el valor del stock restando la cantidad de productos comprados:
    public static void updateStockSubstract(String codProducto, String cantidad) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "UPDATE Producto SET Stock = Stock - ? WHERE Cod_Producto = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, cantidad);
            ps.setString(2, codProducto);
            ps.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
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

    //funcíon que recibe data del tipo object[][] que realiza una transaccion a una tabla en especifico en la base de datos con cada uno de los niveles de profundidad de la data:
    public static void insertCompraDetalle(String idCompra, Object[][] data) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "INSERT INTO Compra_Detalle VALUES (?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            for (Object[] data1 : data) { 
                String idProducto = data1[0].toString();
                String precioUnitario = data1[2].toString();
                String cantidad = data1[3].toString();
                String total = data1[4].toString();
                ps.setString(1, idProducto);
                ps.setString(2, cantidad);
                ps.setString(3, precioUnitario);
                ps.setString(4, total);
                ps.setString(5, idCompra);
                ps.executeUpdate();
            }
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
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

    //funcion que actualiza el stock en la tabla producto, desde compra_detalle:
    public static void updateStockFromCompraDetalle(String idCompra) throws SQLException {
        Connection cn = sqlConnection.getConnection();
        PreparedStatement ps = null;
        try {
            cn.setAutoCommit(false);
            String sql = "UPDATE Productos SET Stock = Stock + (SELECT Cantidad FROM Compra_Detalle WHERE ID_Producto = Productos.ID_Producto AND Cod_Compra = ?) WHERE Productos.ID_Producto IN (SELECT ID_Producto FROM Compra_Detalle WHERE Cod_Compra = ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, idCompra);
            ps.setString(2, idCompra);
            ps.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
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
