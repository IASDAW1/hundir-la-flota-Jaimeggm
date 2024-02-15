/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.hundirlaflota;

/**
 *
 * @author Jaime
 */
import java.util.Random;
import java.util.Scanner;

class Barco {
    int longitud;
    String nombre;
    private boolean[] partes;

    public Barco(int longitud, String nombre) {
        this.longitud = longitud;
        this.nombre = nombre;
        this.partes = new boolean[longitud];
        for (int i = 0; i < longitud; i++) {
            this.partes[i] = false;
        }
    }

    public boolean haSidoHundido() {
        for (boolean parte : partes) {
            if (!parte) {
                return false;
            }
        }
        return true;
    }

    public void hundirParte(int parte) {
        partes[parte] = true;
    }
}
