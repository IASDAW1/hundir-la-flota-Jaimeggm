/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.hundirlaflota;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jaime
 */
class Tablero {
    private final char[][] tablero;
    private final Barco[] barcos;
    private final int filas;
    private final int columnas;

    public Tablero(int filas, int columnas, Barco[] barcos) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new char[filas][columnas];
        this.barcos = barcos;
    }

   

    public void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = '~'; // '~' representa agua
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void colocarBarcos() {
        Random rand = new Random();
        for (Barco barco : barcos) {
            int fila, columna;
            do {
                fila = rand.nextInt(filas);
                columna = rand.nextInt(columnas);
            } while (!puedeColocarBarco(barco, fila, columna));
            colocarBarcoEnTablero(barco, fila, columna);
        }
    }

    public boolean puedeColocarBarco(Barco barco, int fila, int columna) {
        if (fila + barco.longitud > filas || columna + barco.longitud > columnas) {
            return false;
        }

        for (int i = 0; i < barco.longitud; i++) {
            if (tablero[fila][columna + i] != '~') {
                return false;
            }
        }

        return true;
    }

    public void colocarBarcoEnTablero(Barco barco, int fila, int columna) {
        for (int i = 0; i < barco.longitud; i++) {
            tablero[fila][columna + i] = 'X'; // 'X' representa parte de un barco
        }
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            imprimirTablero();
            System.out.println("Ingrese coordenadas para disparar (fila columna):");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
                System.out.println("Coordenadas fuera de rango. Intente nuevamente.");
                continue;
            }

            if (tablero[fila][columna] == 'X') {
                System.out.println("¡Has golpeado un barco!");
                for (Barco barco : barcos) {
                    for (int i = 0; i < barco.longitud; i++) {
                        if (fila >= 0 && fila < filas && columna + i >= 0 && columna + i < columnas && tablero[fila][columna + i] == 'X') {
                            barco.hundirParte(columna + i);
                        }
                    }
                    if (barco.haSidoHundido()) {
                        System.out.println("¡" + barco.nombre + " ha sido hundido!");
                    }
                }
            } else {
                System.out.println("Agua...");
            }

            boolean todosHundidos = true;
            for (Barco barco : barcos) {
                if (!barco.haSidoHundido()) {
                    todosHundidos = false;
                    break;
                }
            }

            if (todosHundidos) {
                System.out.println("¡Felicidades, has hundido todos los barcos!");
                break;
            }
        }
        scanner.close();
    }
}