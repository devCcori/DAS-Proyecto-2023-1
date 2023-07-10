// Compare this snippet from src\test\java\test\db\compraUtilsTest.java:
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.pdv;

/**
 *
 * @author
 */

import javax.swing.JTextArea;

import ccori.uni.dbUtils.pdvUtils;

public class ticketGenerationTest{
    //constructor
    private ticketGenerationTest(){
    }

    public static void main(String[] args) {

        java.util.Date fecha = new java.util.Date();
        String fechaActual = fecha.toString();
        JTextArea ta = new JTextArea();

        String productos[][] = {{"Papas", "2", "20"}, {"Coca", "1", "10"}};
        try{
            pdvUtils.generateXML("1", "abc", fechaActual, "2", productos, "30", ta);
            System.out.println("EUREKAAAA!!!!");
            pdvUtils.generateTicket("1", "abc", fechaActual, "2", productos, "30", ta);
            pdvUtils.printTicket(ta);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}