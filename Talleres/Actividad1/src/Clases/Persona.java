/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author estudiante
 */
public class Persona {

    private double peso;
    private double estatura;
    private double IMC;

    public Persona() {

    }

    public Persona(double peso, double estatura) {
        this.peso = peso;
        this.estatura = estatura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public void setIMC() {
        this.IMC = peso / (estatura * estatura);;
    }

    public double getPeso() {
        return peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public double getIMC() {
        return IMC;
    }

    public String getClasificacion() {
        String clasificacion = "";
        if (IMC < 18.5) {
            clasificacion = "Bajo de peso";
        } else if (IMC > 18.5 && IMC < 24.9) {
            clasificacion = "Normal";
        } else if (IMC > 25.0 && IMC < 29.9) {
            clasificacion = "Sobrepeso";
        } else {
            clasificacion = "Obesidad";
        }
        return clasificacion;
    }

}
