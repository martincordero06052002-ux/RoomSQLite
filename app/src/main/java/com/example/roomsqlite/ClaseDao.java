package com.example.roomsqlite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClaseDao {
    @Insert
    void insert(Clase clase);

    @Query("SELECT * FROM clase ORDER BY nombre ASC")
    LiveData<List<Clase>> obtenerClases();
}