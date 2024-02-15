/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.hundirlaflota;

/**
 *
 * @author Jaime
 */
        

public class HundirLaFlota {
    public static void main(String[] args) {
        Barco[] barcos = {
            new Barco(3, "Destructor"),
            new Barco(4, "Submarino"),
            new Barco(5, "Portaaviones")
        };

        Tablero tablero = new Tablero(10, 10, barcos);
        tablero.inicializarTablero();
        tablero.colocarBarcos();
        tablero.jugar();
    }
}
