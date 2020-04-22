package com.darwin.tetris.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Puntuacion {
    @PrimaryKey(autoGenerate = true)
    public int pid;
    @ColumnInfo(name = "Nombre")
    public String nombre;
    @ColumnInfo(name = "Puntuacion")
    public int puntuacion;

    public Puntuacion(String nombre,int puntuacion){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }
}
