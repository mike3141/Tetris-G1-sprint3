package com.darwin.tetris.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Puntuacion.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PuntuacionDao puntuacionDao();
}
