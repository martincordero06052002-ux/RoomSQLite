package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomsqlite.Alumno;

import java.util.List;

import repositorio.AlumnosRepositorio;

public class AlumnoViewModel extends AndroidViewModel {
    private AlumnosRepositorio alumnosRepositorio;

    public AlumnoViewModel(@NonNull Application application) {
        super(application);
        alumnosRepositorio = new AlumnosRepositorio(application);
    }

    public LiveData<List<Alumno>> obtener() {
        return alumnosRepositorio.obtener();
    }

    public void insertar(Alumno alumno) { alumnosRepositorio.insertar(alumno); }
    public void eliminar(Alumno alumno) { alumnosRepositorio.eliminar(alumno); }
    public void actualizar(Alumno alumno) { alumnosRepositorio.actualizar(alumno); }
}