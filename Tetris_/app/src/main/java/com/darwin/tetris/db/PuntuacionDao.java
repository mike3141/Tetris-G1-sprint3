package com.darwin.tetris.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PuntuacionDao {

    @Query("SELECT * FROM puntuacion ORDER BY Puntuacion DESC LIMIT 10")
    List<Puntuacion> getAll();
    @Insert
    void insert(Puntuacion p);
    @Delete
    void delete(Puntuacion p);
}
