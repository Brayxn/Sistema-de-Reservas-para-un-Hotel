/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reservahotel;

class Habitacion {
    private String tipo;
    private double precioNoche;
    int maxHuespedes;
    String comodidades;
    private boolean disponible;

    public Habitacion(String tipo, double precioNoche, int maxHuespedes, String comodidades) {
        this.tipo = tipo;
        this.precioNoche = precioNoche;
        this.maxHuespedes = maxHuespedes;
        this.comodidades = comodidades;
        this.disponible = true;
    }

    // Getters y setters
    public String getTipo() {
        return tipo;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public int getMaxHuespedes() {
        return maxHuespedes;
    }

    public String getComodidades() {
        return comodidades;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}