/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ccori.uni.dbUtils;

/**
 *
 * @author devCcori
 */

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

      //genera una funcion para gneerar el xml de una venta teniendo el nombre del cliente, cajero, fecha actual, numero de ticket productos en la forma [][3] (nombre del producto, cantidad, subtotal), y el total de la compra:
      public static void generateXML(String nCliente, String Cajero, String fechaActual, String nTicket, String productos[][], String total, JTextArea txtArea) throws Exception {

            // Genera un documento XML:
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // Genera el elemento raíz:
            Element root = document.createElement("Ticket");
            document.appendChild(root);

            // Genera el elemento Cliente:
            Element clienteElement = document.createElement("Cliente");
            clienteElement.appendChild(document.createTextNode(nCliente));
            root.appendChild(clienteElement);

            // Genera el elemento Cajero:
            Element cajeroElement = document.createElement("Cajero");
            cajeroElement.appendChild(document.createTextNode(Cajero));
            root.appendChild(cajeroElement);

            // Genera el elemento Fecha:
            Element fechaElement = document.createElement("Fecha");
            fechaElement.appendChild(document.createTextNode(fechaActual));
            root.appendChild(fechaElement);

            // Genera el elemento Ticket:
            Element ticketElement = document.createElement("NumeroTicket");
            ticketElement.appendChild(document.createTextNode(nTicket));
            root.appendChild(ticketElement);

            // Genera el elemento Productos:
            Element productosElement = document.createElement("Productos");
            root.appendChild(productosElement);
            for (String[] producto : productos) {
                  Element productoElement = document.createElement("Producto");
                  productosElement.appendChild(productoElement);
                  Element nombreElement = document.createElement("Nombre");
                  nombreElement.appendChild(document.createTextNode(producto[0]));
                  productoElement.appendChild(nombreElement);
                  Element cantidadElement = document.createElement("Cantidad");
                  cantidadElement.appendChild(document.createTextNode(producto[1]));
                  productoElement.appendChild(cantidadElement);
                  Element subtotalElement = document.createElement("Subtotal");
                  subtotalElement.appendChild(document.createTextNode(producto[2]));
                  productoElement.appendChild(subtotalElement);
            }

            // Genera el elemento Total:
            Element totalElement = document.createElement("Total");
            totalElement.appendChild(document.createTextNode(total));
            root.appendChild(totalElement);

            // Obtiene el directorio actual de la aplicación:
            String appDir = System.getProperty("user.dir");

            // Genera la ruta de la carpeta y el archivo XML:
            String folderPath = appDir + "\\pdv\\xml\\";
            String filePath = folderPath + "Ticket_" + nTicket + ".xml";

            // Crea la carpeta si no existe:
            File folder = new File(folderPath);
            if (!folder.exists()) {
                  if (folder.mkdirs()) {
                        System.out.println("Carpeta creada: " + folderPath);
                  } else {
                        System.out.println("No se pudo crear la carpeta: " + folderPath);
                  }
            }

            // Crea el archivo XML:
            File file = new File(filePath);
            if (file.createNewFile()) {
                  System.out.println("Archivo creado: " + filePath);
            } else {
                  System.out.println("El archivo ya existe.");
            }

            // Escribe el contenido en el archivo XML:
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(file);
            transformer.transform(source, result);

            try ( 
                  // Imprime el archivo en el JTextArea:
                  java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(filePath))
            ) {
            String line;
            while ((line = br.readLine()) != null) {
                  txtArea.append(line + "\n");
            }
            }
      }

      //genera una funcion para generar el ticket de una venta teniendo el nombre del cliente, cajero, fecha actual, numero de ticket productos en la forma [][3] (nombre del producto, cantidad, subtotal), y el total de la compra pero en un jTextArea:
      public static void generateTicket(String nCliente, String Cajero, String fechaActual, String nTicket, String productos[][], String total, JTextArea txtArea) throws Exception {
            txtArea.setText("");
            txtArea.append("Ticket de Venta\n\n");
            txtArea.append("Cliente: " + nCliente + "\n");
            txtArea.append("Cajero: " + Cajero + "\n");
            txtArea.append("Fecha: " + fechaActual + "\n");
            txtArea.append("Ticket: " + nTicket + "\n\n");
            txtArea.append("Productos:\n");
            for (String[] producto : productos) {
                  txtArea.append(producto[0] + " x " + producto[1] + " = " + producto[2] + "\n");
            }
            txtArea.append("\nTotal: " + total + "\n");
      }

      //genera una funcion para imprimir el ticket ajusta el ancho del ticket a 80mm:
      public static void printTicket(JTextArea txtArea) {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pageFormat = job.defaultPage();
            Paper paper = new Paper();
            double mm = 30 * (72 / 25.4); // 80mm en puntos
            double width = mm; // Ancho del ticket en milímetros
            paper.setSize(width, 0); // El alto se ajustará automáticamente según el contenido
            paper.setImageableArea(0, 0, width, 1090); // El valor 10000 es un valor alto para permitir suficiente espacio para el contenido
            pageFormat.setPaper(paper);
            job.setPrintable(txtArea.getPrintable(null, null), pageFormat);

            try {
                  job.printDialog();
                  job.print();
            } catch (PrinterException ex) {
                  System.out.println(ex);
            }
            
      }

      //genera un funcion insertarVenta() que recibe el ID_Venta, ID_Empleado, Total, Metodo de pago, y el ID_Cliente, y las inserta en la tabla Ventas con la coneccion cn:
      public static void insertarVenta(String ID_Venta, String ID_Empleado, String Total, String Metodo, String ID_Cliente) throws Exception {
            Connection cn = sqlConnection.getConnection();
            PreparedStatement ps = null;
            try {
                  cn.setAutoCommit(false);
                  String sql = "INSERT INTO Ventas(ID_Venta, TimeStamp, ID_Empleado, Total, Metodo_pago, ID_Cliente) VALUES(?,GETDATE(),?,?,?,?)";
                  ps = cn.prepareStatement(sql);
                  ps.setString(1, ID_Venta);
                  ps.setString(2, ID_Empleado);
                  ps.setString(3, Total);
                  ps.setString(4, Metodo);
                  ps.setString(5, ID_Cliente);
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

      //genera una función insertarVentaDetalle() que recibe el ID_Venta, Cantidad, Total y el ID_Receta, y las inserta en la tabla Ventas_Detalle con la coneccion cn:
      public static void insertarVentaDetalle(String ID_Venta, String[][] data) throws Exception {
            Connection cn = sqlConnection.getConnection();
            PreparedStatement ps = null;
            try {
                  cn.setAutoCommit(false);
                  String[] idRecetas = getIDRecetas(data[0]);
                  String sql = "INSERT INTO Ventas_Detalle(ID_Venta, Cantidad, Total, ID_Receta) VALUES(?,?,?,?)";
                  ps = cn.prepareStatement(sql);
                  for (String[] data1: data){
                        double value = Double.parseDouble(data1[1]);
                        int intValue = (int) value;
                        ps.setString(1, ID_Venta);
                        ps.setInt(2, intValue);
                        ps.setString(3, data1[3]);
                  }
                  for (String idReceta1: idRecetas){
                        ps.setString(4, idReceta1);
                  }
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

      //genera una función que busca los ID de Recetas en un string[] segun su nombre y los devuelve en un array de String:
      public static String[] getIDRecetas(String[] recetas) throws Exception {
            Connection cn = sqlConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String[] IDRecetas = new String[recetas.length];
            try {
                  String sql = "SELECT ID_Receta FROM Receta WHERE Nombre = ?";
                  ps = cn.prepareStatement(sql);
                  for (int i = 0; i < recetas.length; i++) {
                        ps.setString(1, recetas[i]);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                              IDRecetas[i] = rs.getString("ID_Receta");
                        }
                  }
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
            return IDRecetas;
      }
      
      //generar un funcion para subir la venta a la tabla Carrete_Pedidos que requiere unicamente del ID_Venta y la coneccion cn:
      public static void subirVenta(String ID_Venta) throws Exception {
            Connection cn = sqlConnection.getConnection();
            PreparedStatement ps = null;
            try {
                  cn.setAutoCommit(false);
                  String sql = "INSERT INTO Carrete_Pedidos(ID_Venta,TimeStamp,Estado) VALUES(?,GETDATE(),'Esperando')";
                  ps = cn.prepareStatement(sql);
                  ps.setString(1, ID_Venta);
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

      //funcion que resta el stock de los productos pertenecientes a las recetas vendidas en la venta, donde los productos se obtienen de la tabla Receta_Insumo y se resta de la tabla Producto:
      public static void restarStock(String[][] data) throws Exception {
            Connection cn = sqlConnection.getConnection();
            PreparedStatement ps = null;
            try {
                  cn.setAutoCommit(false);
                  String[] idRecetas = getIDRecetas(data[0]);
                  String sql = "UPDATE Producto SET Stock = Stock - ? WHERE ID_Producto = ?";
                  ps = cn.prepareStatement(sql);
                  for (String[] data1: data){
                        double value = Double.parseDouble(data1[1]);
                        int intValue = (int) value;
                        ps.setInt(1, intValue);
                        ps.setString(2, data1[0]);
                  }
                  for (String idReceta1: idRecetas){
                        ps.setString(4, idReceta1);
                  }
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