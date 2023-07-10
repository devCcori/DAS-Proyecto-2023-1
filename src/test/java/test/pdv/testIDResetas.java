/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.pdv;

/**
 *
 * @author devCcori
 */

import ccori.uni.dbUtils.pdvUtils;

public class testIDResetas {
    //contructor
    public testIDResetas() {
    }

    public static void main(String[] args) {
        String recetas[]  = {"Receta 1", "Receta 2", "Receta 3"};
        try {        
            String idString[] = pdvUtils.getIDRecetas(recetas);
            System.out.println("EUREKAAAA!!!!");
            for (String idString1 : idString) {
                System.out.println(idString1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    

}
