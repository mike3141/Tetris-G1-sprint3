package com.darwin.tetris;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.HashMap;

public class personalizarPiezas extends AppCompatActivity {

    private HashMap<String,Integer> piezaColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_piezas);
        piezaColor = new HashMap<>();
    }
    public void personalizarPieza_1(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor);
                TextView tv = findViewById(R.id.pieza_1);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_2(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor2);
                TextView tv = findViewById(R.id.pieza_2);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_3(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor3);
                TextView tv = findViewById(R.id.pieza_3);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_4(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor4);
                TextView tv = findViewById(R.id.pieza_4);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_5(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor5);
                TextView tv = findViewById(R.id.pieza_5);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_6(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor6);
                TextView tv = findViewById(R.id.pieza_6);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void personalizarPieza_7(View view){
        final ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(this);
        builder.setTitle("Selector de colores");
        builder.setPositiveButton("Confirmar", new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {

                Button btn = findViewById(R.id.cambColor7);
                TextView tv = findViewById(R.id.pieza_7);
                piezaColor.put(tv.getText().toString(),envelope.getColor());
                btn.setBackgroundColor(envelope.getColor());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.attachAlphaSlideBar(false);
        builder.attachBrightnessSlideBar(false);
        builder.show();
    }
    public void aplicar(View view){
        Intent intMainActivity = new Intent(this,MainActivity.class);
        intMainActivity.putExtra("hasmapRelleno",piezaColor);
        startActivity(intMainActivity);
        finish();
    }
}
