package com.example.roomsqlite;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumno",
        foreignKeys = @ForeignKey(entity = Clase.class,
                parentColumns = "id",
                childColumns = "claseId",
                onDelete = ForeignKey.CASCADE))
public class Alumno {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    public float nota;

    @ColumnInfo(name = "claseId")
    public int claseId;

    public Alumno(String nombre, float nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setClaseId(int claseId) { this.claseId = claseId; }
}