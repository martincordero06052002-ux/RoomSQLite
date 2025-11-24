package com.example.roomsqlite;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clase")
public class Clase {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;

    public Clase(String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
}