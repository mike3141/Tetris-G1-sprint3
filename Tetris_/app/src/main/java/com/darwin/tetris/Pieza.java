package com.darwin.tetris;


public class Pieza {

    // Atributos
    private int x1,x2,x3,x4;
    private int y1,y2,y3,y4;
    private int tipo;

    /*
    * piezas que tendra nuestro juego
    * 1 cuadrado
    * 2 Z
    * 3 I
    * 4 T
    * 5 S
    * 6 J
    * 7 L
    * */

    public Pieza(Pieza pieza){
        this.x1 = pieza.getX1(); this.y1 = pieza.getY1();
        this.x2 = pieza.getX2(); this.y2 = pieza.getY2();
        this.x3 = pieza.getX3(); this.y3 = pieza.getY3();
        this.x4 = pieza.getX4(); this.y4 = pieza.getY4();
        this.tipo = pieza.getTipo();
    }
    public Pieza(int numero){
        switch (numero){
            case 1:
                x1=0;y1=7;
                x2=0;y2=8;
                x3=1;y3=7;
                x4=1;y4=8;
                tipo = 1;
                break;
            case 2:
                x1=0;y1=7;
                x2=0;y2=8;
                x3=1;y3=8;
                x4=1;y4=9;
                tipo = 2;
                break;
            case 3:
                x1=0;y1=6;
                x2=0;y2=7;
                x3=0;y3=8;
                x4=0;y4=9;
                tipo = 3;
                break;
            case 4:
                x1=0;y1=8;
                x2=1;y2=7;
                x3=1;y3=8;
                x4=1;y4=9;
                tipo = 4;
                break;
            case 5:
                x1=0;y1=7;
                x2=0;y2=8;
                x3=1;y3=6;
                x4=1;y4=7;
                tipo = 5;
                break;
            case 6:
                x1=0;y1=9;
                x2=0;y2=8;
                x3=0;y3=7;
                x4=1;y4=9;
                tipo = 6;
                break;
            case 7:
                x1=0;y1=7;
                x2=0;y2=8;
                x3=0;y3=9;
                x4=1;y4=7;
                tipo = 7;
                break;
        }
    }

    public void mover(int x, int y){
        x1 = x1+x; y1 = y1+y;
        x2 = x2+x; y2 = y2+y;
        x3 = x3+x; y3 = y3+y;
        x4 = x4+x; y4 = y4+y;
    }
    public int getTipo() {
        return tipo;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getX3() {
        return x3;
    }

    public int getX4() {
        return x4;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getY3() {
        return y3;
    }

    public int getY4() {
        return y4;
    }

    public void rotarPieza(){
        int temporalX1,temporalX2,temporalX3;
        int temporalY1,temporalY2,temporalY3;

        temporalX1 = (x1+y2-y1);
        temporalY1 = (y1-x2+x1);
        x2 = temporalX1;
        y2 = temporalY1;

        temporalX2 = (x1+y3-y1);
        temporalY2 = (y1-x3+x1);
        x3= temporalX2;
        y3= temporalY2;

        temporalX3 = (x1+y4-y1);
        temporalY3 = (y1-x4+x1);
        x4= temporalX3;
        y4= temporalY3;
    }

    public int minCoordenadaEnX(int x1,int x2,int x3,int x4){
        return Math.min(Math.min(x1,x2),Math.min(x3,x4));
    }

}
