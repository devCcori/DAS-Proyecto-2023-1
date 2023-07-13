/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ccori.uni.compras;

/**
 *
 * @author devCcori
 */
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Objects;

import java.util.concurrent.atomic.AtomicBoolean;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

import ccori.uni.data.Empleados;
import ccori.uni.data.Proveedores;
import ccori.uni.dbUtils.compraUtils;
import ccori.uni.dbUtils.pdvUtils;
import ccori.uni.dbUtils.tableUtils;


public class New_Compra extends javax.swing.JFrame {

    /**
     * Creates new form Compras_MainView
     */
    public New_Compra() {
        initComponents();
        actualizarHora();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
            
        //modifica la primera columna del JTable para que se vuelva un combobox qeu obtenga sus opciones de pdvUtils.getComboBoxModel("Productos", "ID_Producto"):
        JComboBox<String> comboBox = new JComboBox<>();
        try {
            comboBox.setModel(pdvUtils.getComboBoxModel("Productos", "ID_Producto"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        TableColumn col = tblCompra.getColumnModel().getColumn(0);
        col.setCellEditor(new DefaultCellEditor(comboBox));
        //modifica la segunda columna del JTable para que se vuelva un combobox qeu obtenga sus opciones de pdvUtils.getComboBoxModel("Productos", "Nombre_Producto"):
        JComboBox<String> comboBox2 = new JComboBox<>();
        try {
            comboBox2.setModel(pdvUtils.getComboBoxModel("Productos", "Nombre_Producto"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        TableColumn col2 = tblCompra.getColumnModel().getColumn(1);
        col2.setCellEditor(new DefaultCellEditor(comboBox2));

        AtomicBoolean isListenerActive = new AtomicBoolean(false);

        //agrega un listener para cuando la columna 1 o 2 cambien de valor:
        tblCompra.getModel().addTableModelListener((var e) -> {
            //check if last row is not empty:
            int lastRow = tblCompra.getRowCount() - 1;
            if (tblCompra.getValueAt(lastRow, 0) != null || tblCompra.getValueAt(lastRow, 1) != null) {
                if (!isListenerActive.get()) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    Object value = tblCompra.getValueAt(row, column);
                    String stringValue;
                    if (value instanceof String) {
                        stringValue = (String) value;
                    } else if (value instanceof Double) {
                        double doubleValue = (double) value;
                        stringValue = String.valueOf(doubleValue);
                    } else {
                        stringValue = "";
                    }
                    if (column == 0) {
                        isListenerActive.set(true);
                        stringValue = tblCompra.getValueAt(row, column).toString();
                        String precio = "";
                        double numericValue;
                        try {
                            precio = compraUtils.getValue("SELECT Costo FROM Productos WHERE ID_Producto = '" + stringValue +"'");
                            try {
                                numericValue = Double.parseDouble(precio);
                            } catch (NumberFormatException e3) {
                                numericValue = 0.0;
                            }
                            tblCompra.setValueAt(compraUtils.getValue("SELECT Nombre_Producto FROM Productos WHERE ID_Producto = '" + stringValue + "'"), row, column + 1);
                            tblCompra.setValueAt(numericValue , row, column+2);
                        } catch (SQLException e2) {
                            System.out.println(e2);
                        }
                    }
                    if (column == 1){
                        isListenerActive.set(true);
                        stringValue = tblCompra.getValueAt(row, column).toString();
                        String precio = "";
                        double numericValue;
                        try {
                            precio = compraUtils.getValue("SELECT Costo FROM Productos WHERE Nombre_Producto = '" + stringValue +"'");
                            try {
                                numericValue = Double.parseDouble(precio);
                            } catch (NumberFormatException e3) {
                                numericValue = 0.0;
                            }
                            tblCompra.setValueAt(compraUtils.getValue("SELECT ID_Producto FROM Productos WHERE Nombre_Producto = '" + stringValue + "'"), row, column - 1);
                            tblCompra.setValueAt(numericValue, row, column+1);

                        } catch (SQLException e3) {
                            System.out.println(e3);
                        }
                    }       
                    tblCompra.changeSelection(row, 3, false, false);
                    isListenerActive.set(false);
                }
            }
        });
        txtIdEmpleado.setEditable(false);
        txtIdProveedor.setEditable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIdProveedor = new javax.swing.JTextField();
        btnProveedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        txtTelf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        btnEmpleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INGRESO DE COMPRAS");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ID EMPLEADO");

        txtIdProveedor.setEditable(false);

        btnProveedor.setText("...");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("PROVEEDOR");

        txtProveedor.setEditable(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("RUC / RS");

        txtRUC.setEditable(false);

        txtTelf.setEditable(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Telefono");

        labelHora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelHora.setText("jLabel5");

        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String[]{
                "ID", "Producto", "P/U", "Cantidad", "Total"
            }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                true, true, true, true, false
            };

            // Define que tipo de dato puede ingresar el usuario en cada columna
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            };

            // Define que columnas pueden ser editables
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            };

            // Sobreescribe el método setValueAt para calcular el valor de la columna "Total"
            @Override
            public void setValueAt(Object value, int row, int column) {
                super.setValueAt(value, row, column);
                if (column == 3 || column == 2) { // Cantidad column index
                    Object cantidadValue = getValueAt(row, 2);
                    Object precioValue = getValueAt(row, 3);
                    if (cantidadValue != null && precioValue != null) {
                        Double cantidad = (Double) cantidadValue;
                        Double precioUnitario = (Double) precioValue;
                        Double subtotal = cantidad * precioUnitario;
                        if (subtotal != 0.0){
                            super.setValueAt(subtotal, row, 4); // Total column index
                        }else{
                            super.setValueAt(0.0, row, 4); // Total column index
                            }

                        // Llena el txtTotal con la suma de la ultima columna de la tabla:
                        Double sum = 0.0;
                        for (int i = 0; i < getRowCount(); i++) {
                            Object val = getValueAt(i, 4);
                            if (val != null) {
                                sum += (Double) val;
                            }
                        }
                        txtTotal.setText(sum.toString());

                        
                    } else {
                        super.setValueAt(0.0, row, 4); // Total column index
                    }
                    //agrega una nueva fila si los valores de la ultima fila en las columnas 0 y 1 no son nulos:
                    int lastRow = getRowCount() - 1;
                    if (getValueAt(lastRow, 0) != null && getValueAt(lastRow, 1) != null) {
                        addEmptyRow();
                    }
                }
                if (column == 0){
                    int lastRow = getRowCount() - 1;
                    if (getValueAt(lastRow, 4) != null) {
                        addEmptyRow();
                    }
                }

            };


            // Método para agregar una nueva fila vacía
            private void addEmptyRow() {
                Object[] rowData = new Object[getColumnCount()];
                for (int i = 0; i < getColumnCount(); i++) {
                    rowData[i] = null;
                }
                addRow(rowData);
            }
        });
        jScrollPane1.setViewportView(tblCompra);

        txtTotal.setEditable(false);
        txtTotal.setText("0.00");

        btnComprar.setText("Subir \nCompra");
        btnComprar.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnComprarActionPerformed(evt);
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ID PROVEDOR");

        txtIdEmpleado.setEditable(false);

        btnEmpleado.setText("...");
        btnEmpleado.addActionListener((java.awt.event.ActionEvent evt) -> {
            btnEmpleadoActionPerformed(evt);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelf, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnComprar)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCpmprarActionPerformed
        Object[][] datos = tableUtils.getTableData(tblCompra);
        AtomicBoolean flag = new AtomicBoolean(true);
        //revisa si datos contiene algun null y deuvleve true si lo contiene:
        if (Arrays.stream(datos).flatMap(Arrays::stream).anyMatch(Objects::isNull)){
            JOptionPane.showMessageDialog(null, "No se puede realizar la compra, revise que no haya campos vacios");
            flag.set(false);
        }
        
        if (flag.get()){
            String idCompra = "";
            String querry = """
                    SELECT ISNULL(Cod_Compra, '') as Cod_Compra 
                    FROM Compra_Total
                    WHERE Fecha = (
                        SELECT MAX(Fecha) 
                        FROM Compra_Total
                    )
                    """;
            try {
                idCompra = compraUtils.getValue(querry);
            } catch (SQLException e) {
                System.out.println(e);
            }
            if (idCompra.equals("")){
                idCompra = "C0001";
            } else {
                idCompra = "C" + String.format("%04d", Integer.parseInt(idCompra.substring(1, 5)) + 1);
            }
            if (txtIdProveedor.getText().equals("") || txtIdEmpleado.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Seleccione un proveedor y/o un empleado");
                return;
            } else if(true){
                String total = txtTotal.getText();
                String idProveedor = txtIdProveedor.getText();
                String idEmpleado = txtIdEmpleado.getText();
                try {
                    compraUtils.insertCompraTotal(idCompra, total, idProveedor, idEmpleado);
                    JOptionPane.showMessageDialog(null, "Compra realizada"); 
                } catch (SQLException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error al insertar en la tabla Compra_Total");
                }
            
                try {
                    compraUtils.insertCompraDetalle(idCompra, datos);
                    System.out.println("Compra realizada");
                } catch (SQLException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error al insertar en la tabla Compra_Detalle");
                }
                try {
                    compraUtils.updateStockFromCompraDetalle(idCompra);
                    System.out.println("Stock actualizado");
                    JOptionPane.showMessageDialog(null, "Stock actualizado");
                } catch (SQLException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error al actualizar el stock");
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_btnCpmprarActionPerformed

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        Empleados empleados = new Empleados();
        empleados.setVisible(true);

        empleados.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent){
                Object[] rowData = empleados.getSelectedRowData();
                if (rowData != null){
                    String id = rowData[0].toString();

                    txtIdEmpleado.setText(id.trim());
                }
            }
        });
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {
        Proveedores proveedores = new Proveedores();
        proveedores.setVisible(true);

        proveedores.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                Object[] rowData = proveedores.getSelectedRowData();
                if (rowData != null) {
                    String id = rowData[0].toString();
                    String nombre = rowData[1].toString();
                    String ruc = rowData[2].toString();
                    String telf = rowData[3].toString();

                    txtIdProveedor.setText(id.trim());
                    txtProveedor.setText(nombre.trim());
                    txtRUC.setText(ruc.trim());
                    txtTelf.setText(telf.trim());
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(New_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new New_Compra().setVisible(true);
        });
    }

    //define una funcion actualizarHora() que actualize el labelHora cada segundo:
    public final void actualizarHora() {
        Timer timer = new Timer("HoraActualizada");//Crea un timer y le asigna un nombre
        TimerTask task = new TimerTask() {//Crea una tarea para el timer
            @Override
            public void run() {
                labelHora.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            }
        };
        timer.schedule(task, 0, 1000);//Asigna la tarea al timer y lo ejecuta cada 1000 milisegundos
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelHora;
    private javax.swing.JTable tblCompra;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtTelf;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
