//crea el esquema base para esta clase e importa compraUtils y transactions:
// Compare this snippet from src\test\java\test\db\compraUtilsTest.java:
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.compraUtils;

/**
 *
 * @author
 */

import java.sql.SQLException;

import ccori.uni.dbUtils.compraUtils;

public class compraUtilsTest {

    //constructor
    private compraUtilsTest(){
    }
    
    //crea un test para subir datos a la tabla Compra_Total:
    public static void main(String[] args) {
        try{
            compraUtils.insertCompraTotal("C0003", "1000", "ID001","E001");
            System.out.println("EUREKAAAA!!!!");
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
}