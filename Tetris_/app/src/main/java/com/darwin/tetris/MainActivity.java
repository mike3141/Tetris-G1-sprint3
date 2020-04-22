package com.darwin.tetris;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String,Integer> coloresPiezas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // peticion de permisos
        isStoragePermissionGranted();
        // hacer la app fullscreeen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        coloresPiezas = new HashMap<>();
        Intent obtenerColoresCustom = getIntent();
        coloresPiezas = (HashMap<String, Integer>) obtenerColoresCustom.getSerializableExtra("hasmaprelleno");
        setContentView(R.layout.activity_main);
    }

    // metodos onClicks de los botones

    // lazar el juego
    public void jugar(View view){
        Intent intent = new Intent(this, ActivityJuego.class);
        intent.putExtra("colores",coloresPiezas);
        startActivity(intent);
        finish();
    }

    // lanza la personalizacion de colores para las piezas
    public void personalizaPiezas(View view){
        Intent intent = new Intent(this, personalizarPiezas.class);
        startActivity(intent);
        finish();
    }

    // lanzar la vista del ranking
    public void verRanking(View view){
        Intent intent = new Intent(this, VistaRanking.class);
        startActivity(intent);
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                return false;
            }
        }else {
            return true;
        }
    }
}
