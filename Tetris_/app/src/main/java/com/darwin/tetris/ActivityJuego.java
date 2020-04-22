package com.darwin.tetris;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;

public class ActivityJuego extends AppCompatActivity {

    // atributos

    private Tablero tablero;
    private Vista v;
    private VistaPiezaSiguiente vps;
    private TextView puntuacion;
    private TextView nivel;
    private Button reset;
    private static MediaPlayer[] mediaPlayer;
    private CountDownTimer count;
    private CountDownTimer countFilas;
    private CountDownTimer countMusic;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_juego);
        tablero = new Tablero();
        puntuacion = findViewById(R.id.puntuacion);
        nivel = findViewById(R.id.nivel);
        reset = findViewById(R.id.reset);
        // vista de la zona de puntuacion,nivel y pieza siguiente
        ConstraintLayout piezaSiguiente = findViewById(R.id.pieza_siguiente);
        vps = new VistaPiezaSiguiente(this,tablero);
        piezaSiguiente.addView(vps);
        // vistas de la zona de juego
        RelativeLayout zonaJuego = (RelativeLayout) findViewById(R.id.zona_de_juego);
        v = new Vista(this,tablero,vps);
        v.setMain(this);
        Intent intentColores=getIntent();
        v.setLevel(nivel);
        v.setPoints(puntuacion);
        v.setReinicio(reset);
        it = new Intent(this,ServicioMusica.class);
        startService(it);
        v.setColores((HashMap<String, Integer>) intentColores.getSerializableExtra("colores"));
        zonaJuego.addView(v);
    }

    public void moverDere(View view){v.moverDere();}
    public void moverIzq(View view){
        v.moverIzq();
    }
    public void caidaRapida(View view){v.caidaRapida();}
    public void rotarPieza (View view){
        v.rotar();
    }
    public void reiniciar(View view){
        v.reiniciarPartida();
    }

    // metodos sobreescritos


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        //startService(it);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        stopService(it);
        super.onStop();
    }
}
