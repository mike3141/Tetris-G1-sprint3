package com.darwin.tetris;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {

    // Atributos

    private final int ANCHO = 10; // constante para definir el ancho del tablero
    private final int ALTO = 20; // constante para definir el alto del tablero
    private final int NPIEZAS = 7; // constante pra definir cuantas piezas tiene el juego
    private Random generadorAleatorio;
    private int [][] m_tablero;
    private List<Pieza> listaPiezas = new ArrayList<>();


    // Constructor
    public Tablero(){
        generadorAleatorio = new Random(System.currentTimeMillis());
        m_tablero = new int[ALTO][ANCHO]; // declaramos el tablero
        // creamos dos piezas aleatorias
        listaPiezas.add(new Pieza(generadorAleatorio.nextInt(NPIEZAS)+1)); // pieza a mostrar
        listaPiezas.add(new Pieza(generadorAleatorio.nextInt(NPIEZAS)+1)); // pieza siguiente

    }

    public List<Pieza> getListaPiezas() {
        return listaPiezas;
    }

    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

    public Pieza getPiezaSiguiente(){
        return listaPiezas.get(listaPiezas.size()-1);
    }
    public Pieza getPiezaActual(){
        if(listaPiezas.size()-2 >= 0){
            return listaPiezas.get(listaPiezas.size()-2);
        }
        return null;
    }

    private boolean puedeRotar(Pieza pieza){
        int aux = 0;
        List<Point> auxCoord = new ArrayList<>();
        Pieza auxPieza = new Pieza(pieza);

        Point p1 = new Point(pieza.getX1(),pieza.getY1());
        Point p2 = new Point(pieza.getX2(),pieza.getY2());
        Point p3 = new Point(pieza.getX3(),pieza.getY3());
        Point p4 = new Point(pieza.getX4(),pieza.getY4());

        auxPieza.rotarPieza();

        Point pt1 = new Point(auxPieza.getX1(),auxPieza.getY1());
        Point pt2 = new Point(auxPieza.getX2(),auxPieza.getY2());
        Point pt3 = new Point(auxPieza.getX3(),auxPieza.getY3());
        Point pt4 = new Point(auxPieza.getX4(),auxPieza.getY4());

        auxCoord.add(pt1);
        auxCoord.add(pt2);
        auxCoord.add(pt3);
        auxCoord.add(pt4);

        for (Point p : auxCoord){
            if((p.x < ALTO) && (p.x >= 0) && (p.y >= 0) && (p.y < ANCHO) && (m_tablero[p.x][p.y] == 0)){
                aux++;
            }else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)){
                aux++;
            }
        }

        if(aux == 4){
            return true;
        }
        return false;
    }
    public void caidaRapida(Pieza pieza){
        borrarPieza(pieza);
        while(puedeMoverse(pieza, 1, 0)){
            moverPieza(pieza,1,0);
        }
        ponerPieza(pieza);
    }

    public void ponerPieza(Pieza pieza){
        m_tablero[pieza.getX1()][pieza.getY1()] = pieza.getTipo();
        m_tablero[pieza.getX2()][pieza.getY2()] = pieza.getTipo();
        m_tablero[pieza.getX3()][pieza.getY3()] = pieza.getTipo();
        m_tablero[pieza.getX4()][pieza.getY4()] = pieza.getTipo();
    }

    public void borrarPieza(Pieza pieza){
        m_tablero[pieza.getX1()][pieza.getY1()] = 0;
        m_tablero[pieza.getX2()][pieza.getY2()] = 0;
        m_tablero[pieza.getX3()][pieza.getY3()] = 0;
        m_tablero[pieza.getX4()][pieza.getY4()] = 0;
    }
    public void moverPieza(Pieza pieza,int x, int y){
        borrarPieza(pieza);
        pieza.mover(x,y);
        ponerPieza(pieza);
    }

    public int limpiarFilas(){
        int filaBorrada;
        int n_filas_borradas = 0;
        List<Integer> listaDeFilasBorradas = new ArrayList<>();

        for (int i = 0;i<ALTO;i++){
            for (int j = (ANCHO-1);j >= 0;j--){
                if(m_tablero[i][j] == 0 || m_tablero[i][j] == 9){
                    break;
                }
                if (j == 0){
                    filaBorrada = i;
                    listaDeFilasBorradas.add(filaBorrada);
                    n_filas_borradas++;
                    borrarFila(filaBorrada);
                }
            }
        }

        if(n_filas_borradas >= 1){
            int fila_mas_alta = Collections.min(listaDeFilasBorradas);
            int [][] copia_tablero = new int[fila_mas_alta][ANCHO];

            for (int i = 0;i<copia_tablero.length;i++){
                for (int j = 0;j < copia_tablero[1].length;j++){
                    copia_tablero[i][j] = m_tablero[i][j];
                }
            }
            for (int i = 0;i<copia_tablero.length;i++){
                for (int j = 0;j < copia_tablero[1].length;j++){
                    m_tablero[i+n_filas_borradas][j] = copia_tablero[i][j];
                }
            }
        }
        return n_filas_borradas;
    }

    public void borrarFila(int index){
        for (int i = 0;i < ANCHO;i++){
            m_tablero[index][i] = 0;
        }
    }

    public boolean puedeMoverse(Pieza pieza,int x,int y){
        int tmp  =0;

        if(pieza != null){
            Point p1 = new Point(pieza.getX1(),pieza.getY1());
            Point p2 = new Point(pieza.getX2(),pieza.getY2());
            Point p3 = new Point(pieza.getX3(),pieza.getY3());
            Point p4 = new Point(pieza.getX4(),pieza.getY4());

            Point pt1 = new Point(pieza.getX1()+x,pieza.getY1()+y);
            Point pt2 = new Point(pieza.getX2()+x,pieza.getY2()+y);
            Point pt3 = new Point(pieza.getX3()+x,pieza.getY3()+y);
            Point pt4 = new Point(pieza.getX4()+x,pieza.getY4()+y);

            List<Point> temp = new ArrayList<>();
            temp.add(pt1);
            temp.add(pt2);
            temp.add(pt3);
            temp.add(pt4);

            for(Point p : temp){
                if(p.x<ALTO && p.y >= 0 && p.y < ANCHO && m_tablero[p.x][p.y] == 0){
                    tmp++;
                }else if(p.equals(p1) || p.equals(p2)|| p.equals(p3) || p.equals(p4)){
                    tmp++;
                }
            }
            return tmp == 4;
        }
        return false;

    }

    public void gravedadPieza(Pieza pieza){
        if(puedeMoverse(pieza, 1, 0)){
            moverPieza(pieza,1,0);
        }

    }
    public void moverIzquierda(Pieza pieza){
        if(puedeMoverse(pieza, 0, -1)){
            moverPieza(pieza,0,-1);
        }

    }
    public void moverDerecha(Pieza pieza){
        if(puedeMoverse(pieza, 0, 1)){
            moverPieza(pieza,0,1);
        }

    }

    public void rotarPieza(Pieza pieza){
        if(puedeRotar(pieza) && pieza.getTipo() != 1){
            borrarPieza(pieza);
            pieza.rotarPieza();
            ponerPieza(pieza);
        }
        ponerPieza(pieza);
    }

    public boolean comprobarFinDeJuego(Pieza pieza){
        if(pieza != null){
            return !puedeMoverse(pieza, 1, 0) && (pieza.minCoordenadaEnX(pieza.getX1(), pieza.getX2(), pieza.getX3(), pieza.getX4()) <= 1);
        }
        return false;
    }
    public int[][] getM_tablero() {
        return m_tablero;
    }
}
