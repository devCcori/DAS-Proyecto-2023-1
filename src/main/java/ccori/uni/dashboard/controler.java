/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ccori.uni.dashboard;

/**
 *
 * @author devCcori
 */

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class controler {
    //constructor
    private controler() {
    }

    //crea un pie chart con 2 totales y lo muestra en un Panel el cual se le pasa:
    //titulo, total1, total2, jPanel:
    public static void pieChart(String title, double total1, double total2, JPanel jPanel) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Compras", total1);
        dataset.setValue("Ventas", total2);
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, true);
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel.removeAll();
        jPanel.add(chartPanel, BorderLayout.CENTER);
        jPanel.validate();
        
    }
}