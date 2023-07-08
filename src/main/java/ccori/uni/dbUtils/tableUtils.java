/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ccori.uni.dbUtils;

/**
 *
 * @author devCcori
 */

import javax.swing.JTable;


public class tableUtils {
    //constructor
    private tableUtils(){
    }

    //define una funcion que obtiene todos los datos de una jTable por filas, y los devuelve en un array:
    public static Object[][] getTableData(JTable table) {
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();
        Object[][] data = new Object[rowCount-1][columnCount];

        for (int i = 0; i < rowCount-1; i++) {
            for (int j = 0; j < columnCount; j++) {
                data[i][j] = table.getValueAt(i, j);
            }
        }

        return data;
    }

}
