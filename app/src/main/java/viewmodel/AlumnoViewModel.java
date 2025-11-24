package viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.Clase;
import java.util.List;
import repositorio.AlumnosRepositorio;

public class AlumnoViewModel extends AndroidViewModel {
    private AlumnosRepositorio alumnosRepositorio;

    public AlumnoViewModel(@NonNull Application application) {
        super(application);
        alumnosRepositorio = new AlumnosRepositorio(application);
    }

    // Alumnos
    public LiveData<List<Alumno>> obtener() { return alumnosRepositorio.obtener(); }

    public LiveData<List<Alumno>> obtenerPorClase(int claseId) {
        return alumnosRepositorio.obtenerPorClase(claseId);
    }

    public void insertar(Alumno alumno) { alumnosRepositorio.insertar(alumno); }
    public void eliminar(Alumno alumno) { alumnosRepositorio.eliminar(alumno); }
    public void actualizar(Alumno alumno) { alumnosRepositorio.actualizar(alumno); }

    // Clases
    public LiveData<List<Clase>> obtenerClases() { return alumnosRepositorio.obtenerClases(); }
    public void insertarClase(Clase clase) { alumnosRepositorio.insertarClase(clase); }
}