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

    @Query("SELECT * FROM Alumno")
    LiveData<List<Alumno>> getAlumnos();

    @Query("SELECT * FROM alumno WHERE Id LIKE :uuid")
    Alumno getAlumno(String uuid);

    @Insert
    void addAlumno(Alumno alumno);

    @Delete
    void deleteAlumno(Alumno alumno);

    @Update
    void updateAlumno(Alumno alumno);
}
