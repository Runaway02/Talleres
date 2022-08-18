/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Clases.Persona;
import javax.swing.JOptionPane;

/**
 *
 * @author estudiante
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double peso;
        double estatura;
        peso = Double.parseDouble(JOptionPane.showInputDialog("Digite su peso"));
        estatura = Double.parseDouble(JOptionPane.showInputDialog("Digite su estatura"));
        Persona persona1 = new Persona(peso, estatura);
        persona1.setIMC();
        JOptionPane.showMessageDialog(null, "Su indice de masa corporal es: " + persona1.getIMC() + "\nSu clasificación es: " + persona1.getClasificacion());
        Persona persona2 = new Persona(58, 1.74);
        persona2.setIMC();
        System.out.println("Su indice de masa corporal es: " + persona2.getIMC() + "\nSu clasificación es: " + persona2.getClasificacion());
    }

}
