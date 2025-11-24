package com.example.roomsqlite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlumnoDao {

    // Obtener todos los alumnos (existente)
    @Query("SELECT * FROM Alumno")
    LiveData<List<Alumno>> getAlumnos();
    @Query("SELECT * FROM alumno WHERE claseId = :claseId ORDER BY nombre ASC")
    LiveData<List<Alumno>> getAlumnosPorClase(int claseId);

    @Query("SELECT * FROM alumno WHERE id = :id")
    Alumno getAlumno(int id);

    @Insert
    void addAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);
}