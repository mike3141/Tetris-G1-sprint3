package com.darwin.tetris;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Vista extends View{

    // Atributos
    private Tablero tablero;
    private VistaPiezaSiguiente vps;
    private Timer timer;
    private ActivityJuego main;
    private int tiempo;
    private Random aleatorio;
    private int puntuacion;
    private int nivel;
    private final int puntos_por_fila = 50;
    private HashMap<String,Integer> colores;
    private TextView points;
    private TextView level;
    private int cambio;
    private final int SECONDS = 30000; // esto seran nuestros 30 segundos
    private final int DESCUENTO_DE_TIEMPO = 1000; // es el valor que se restara cada vez en al tiempo
    private CountDownTimer count;
    private CountDownTimer countFilas;
    private CountDownTimer countMusic;
    private boolean unaFilaBorrada;
    private boolean dosOMasFilasBorradas;
    private int colorAleatorio;
    private Button reinicio;
    private boolean reset;


    public Vista(Context context, Tablero tablero, VistaPiezaSiguiente vistaPiezaSiguiente){
        super(context);
        this.tablero = tablero;
        this.vps = vistaPiezaSiguiente;
        timer = new Timer();
        tiempo = 500;
        aleatorio = new Random(System.currentTimeMillis());
        puntuacion = 0;
        nivel = 0;
        points = null;
        level = null;
        cambio = 1;
        InicioJuego();
    }

    public void setMain(com.darwin.tetris.ActivityJuego main) {
        this.main = main;
    }

    public void InicioJuego(){


        // countdown de la pieza kamikace
        count = new CountDownTimer(SECONDS,DESCUENTO_DE_TIEMPO){

            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println("Segundos para que la pieza kamikaze caiga: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                caidaRapida();
                count.start();
            }
        }.start();

        // countdown de las dos filas
        countFilas = new CountDownTimer(50000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("COUNT FILA","Seconds para insertar fila: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                insertarfilas();
                if(countFilas != null){
                    countFilas.start();
                }

            }
        }.start();
        if(reset){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                main.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        reset = false;
                        if (!finDeJuego()){
                            tablero.gravedadPieza(tablero.getPiezaActual());
                            if (!tablero.puedeMoverse(tablero.getPiezaActual(), 1, 0)){
                                int filas_borradas = tablero.limpiarFilas();
                                tablero.limpiarFilas();
                                tablero.getListaPiezas().remove(tablero.getPiezaActual());
                                tablero.getListaPiezas().add(new Pieza(aleatorio.nextInt(7)+1));
                                vps.invalidate();
                                if(filas_borradas > 0){
                                    if(filas_borradas == 1){ // si solo se elimina una linea
                                        unaFilaBorrada = true;
                                    }
                                    if(filas_borradas > 1){ // Si se elimina mas de una linea
                                        Random red = new Random();
                                        Random green = new Random();
                                        Random blue= new Random();
                                        colorAleatorio = Color.rgb(red.nextInt(256),green.nextInt(256),blue.nextInt(256));
                                        dosOMasFilasBorradas = true;
                                    }
                                    puntuacion += filas_borradas*puntos_por_fila;
                                    if(puntuacion > 120){
                                        reinicio.setVisibility(VISIBLE);
                                    }
                                    points.setText("Puntuacion: "+puntuacion);
                                }

                                // funcionalidad del nivel de complejidad del juego
                                if(cambio == 1 && puntuacion >=100){
                                    nivel = 1;
                                    subirNivel(nivel);
                                    cambio++;
                                }else if(cambio == 2 && puntuacion >=200){
                                    nivel = 2;
                                    subirNivel(nivel);
                                    cambio++;
                                }else if(cambio == 3 && puntuacion >=400){
                                    nivel = 3;
                                    subirNivel(nivel);
                                    cambio++;
                                }else if(cambio == 4 && puntuacion >=600){
                                    nivel = 4;
                                    subirNivel(nivel);
                                    cambio++;
                                }else if(cambio == 5 && puntuacion >=800){
                                    nivel = 5;
                                    subirNivel(nivel);
                                    cambio++;
                                }

                            }
                            invalidate();
                        }
                    }
                });
            }
        },0,tiempo);
    }
    public void moverIzq(){

        tablero.moverIzquierda(tablero.getPiezaActual());
        invalidate();
    }
    public void moverDere(){
        tablero.moverDerecha(tablero.getPiezaActual());
        invalidate();
    }
    public void caidaRapida(){
        if(tablero.getPiezaActual() != null){
            tablero.caidaRapida(tablero.getPiezaActual());
            invalidate();
        }

    }
    public void rotar(){
        tablero.rotarPieza(tablero.getPiezaActual());
        invalidate();
    }
    public boolean finDeJuego(){
        if(tablero.comprobarFinDeJuego(tablero.getPiezaActual()) && tablero.comprobarFinDeJuego(tablero.getPiezaSiguiente())){
            Log.i("Contador loop: ","Estas cerrando el contador de loop.");
            timer.cancel();
            Log.i("Count piece kamikaze: ","Estas cerrando el contador de pieza kamikaze.");
            count.cancel();
            Log.i("Contador insert line: ","Estas cerrando el contador de insert lines.");
            countFilas.cancel();

            tablero.getListaPiezas().clear();
            // limpiamos el tablero
            for (int i = 0;i<tablero.getALTO();i++){
                for (int j = 0; j < tablero.getANCHO();j++){
                    tablero.getM_tablero()[i][j] = 0;
                }
            }
            main.onStop();
            Intent intent = new Intent(this.main,FinDeJuego.class);
            intent.putExtra("points",puntuacion);
            main.startActivity(intent);

            return true;
        }
        return false;
    }
    private void subirNivel(int nivel){
        level.setText("Nivel: "+nivel);
        tiempo -= nivel*15;
        timer.cancel();
        timer = new Timer();
        InicioJuego();

    }
    public void reiniciarPartida(){
        Log.i("Contador loop: ","Estas cerrando el contador de loop.");
        timer.cancel();
        Log.i("Count piece kamikaze: ","Estas cerrando el contador de pieza kamikaze.");
        count.cancel();
        Log.i("Contador insert line: ","Estas cerrando el contador de insert lines.");
        countFilas.cancel();
        tablero.getListaPiezas().clear();
        // limpiamos el tablero
        for (int i = 0;i<tablero.getALTO();i++){
            for (int j = 0; j < tablero.getANCHO();j++){
                tablero.getM_tablero()[i][j] = 0;
            }
        }
        invalidate();
        puntuacion = 0;
        reinicio.setVisibility(INVISIBLE);
        puntuacion = 0;
        nivel = 0;
        level.setText("Nivel: "+nivel);
        points.setText("Puntuacion: "+puntuacion);
        unaFilaBorrada = false;
        dosOMasFilasBorradas = false;
        reset = true;
        InicioJuego();
    }

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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        if (colores != null){
            for (int i = 0;i<tablero.getALTO();i++){
                for (int j = 0;j< tablero.getANCHO();j++){
                    switch(tablero.getM_tablero()[i][j]){
                        case 0:
                            p.setColor(Color.BLACK);
                            break;
                        case 1:
                            p.setColor(colores.get("Cuadrado"));
                            break;
                        case 2:
                            p.setColor(colores.get("Pieza Z"));
                            break;
                        case 3:
                            p.setColor(colores.get("Pieza I"));
                            break;
                        case 4:
                            p.setColor(colores.get("Pieza T"));
                            break;
                        case 5:
                            p.setColor(colores.get("Pieza S"));
                            break;
                        case 6:
                            p.setColor(colores.get("Pieza J"));
                            break;
                        case 7:
                            p.setColor(colores.get("Pieza L"));
                            break;
                        case 9:
                            p.setColor(Color.parseColor("#6C6C88"));
                            break;
                    }
                    canvas.drawRect(j*70,i*70,j*70+70,i*70+70,p);
                }
            }
        }
        else{
            for (int i = 0;i<tablero.getALTO();i++){
                for (int j = 0;j< tablero.getANCHO();j++){
                    switch(tablero.getM_tablero()[i][j]){
                        case 0:
                            p.setColor(Color.BLACK);
                            break;
                        case 1:
                            p.setColor(Color.YELLOW);
                            break;
                        case 2:
                            p.setColor(Color.RED);
                            break;
                        case 3:
                            p.setColor(Color.CYAN);
                            break;
                        case 4:
                            p.setColor(Color.parseColor("#800080"));
                            break;
                        case 5:
                            p.setColor(Color.GREEN);
                            break;
                        case 6:
                            p.setColor(Color.BLUE);
                            break;
                        case 7:
                            p.setColor(Color.parseColor("#ff8000"));
                            break;
                        case 9:
                            p.setColor(Color.parseColor("#6C6C88"));
                            break;
                    }
                    canvas.drawRect(j*70,i*70,j*70+70,i*70+70,p);
                }
            }
        }
        if(unaFilaBorrada) { // Si se elimina una linea
            dosOMasFilasBorradas = false;
            System.out.println("EL NUMERO DE CANVAS ES : " + canvas.getSaveCount());
            for (int i = 0; i < tablero.getALTO(); i++) {
                for (int j = 0; j < tablero.getANCHO(); j++) {
                    switch (tablero.getM_tablero()[i][j]) {
                        case 0:
                            p.setColor(Color.BLACK);
                            break;
                        default:
                            p.setColor(Color.parseColor("#21610B"));
                            break;
                        case 9:
                            p.setColor(Color.parseColor("#6C6C88"));
                            break;
                    }
                    canvas.drawRect(j*70,i*70,j*70+70,i*70+70,p);
                }
            }
        }
        if(dosOMasFilasBorradas) { // Si se elimina mas de una linea
            unaFilaBorrada = false;
            System.out.println("EL NUMERO DE CANVAS ES : " + canvas.getSaveCount());
            for (int i = 0; i < tablero.getALTO(); i++) {
                for (int j = 0; j < tablero.getANCHO(); j++) {
                    switch (tablero.getM_tablero()[i][j]) {
                        case 0:
                            p.setColor(Color.BLACK);
                            break;
                        case 9:
                            p.setColor(Color.parseColor("#6C6C88"));
                            break;
                        default:
                            p.setColor(colorAleatorio);
                            break;
                    }
                    canvas.drawRect(j*70,i*70,j*70+70,i*70+70,p);
                }
            }
        }
    }

    public void setColores(HashMap<String, Integer> colores) {
        this.colores = colores;
    }

    public void setPoints(TextView points) {
        this.points = points;
    }

    public void setLevel(TextView level) {
        this.level = level;
    }

    public void insertarfilas(){
        if(tablero.getPiezaActual() != null){
            tablero.borrarPieza(tablero.getPiezaActual()); // borrado de pieza
            for (int i = 2; i < tablero.getALTO();i++){
                for (int j = 0; j < tablero.getANCHO();j++){
               /* if(i!=34){
                    tablero.getM_tablero()[i-2][j] = tablero.getM_tablero()[i][j];
                    tablero.getM_tablero()[i-1][j] = tablero.getM_tablero()[i+1][j];
                }else{*/
                    tablero.getM_tablero()[i-2][j] = tablero.getM_tablero()[i][j];
                    tablero.getM_tablero()[i][j] = 9;
                    tablero.getM_tablero()[i-1][j] = 9;
                    //}
                }
            }
            tablero.ponerPieza(tablero.getPiezaActual());
            invalidate();
        }
    }

    public void setReinicio(Button reinicio) {
        this.reinicio = reinicio;
    }
}
