package repositorio;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.AlumnoDao;
import com.example.roomsqlite.AlumnoDataBase;
import com.example.roomsqlite.Clase;
import com.example.roomsqlite.ClaseDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AlumnosRepositorio {
    private AlumnoDao alumnoDao;
    private ClaseDao claseDao; // Nuevo
    private Executor executor = Executors.newSingleThreadExecutor();

    public AlumnosRepositorio(Application application) {
        AlumnoDataBase db = AlumnoDataBase.obtenerInstancia(application);
        alumnoDao = db.getAlumnoDao();
        claseDao = db.getClaseDao();
    }
    public LiveData<List<Alumno>> obtener() {
        return alumnoDao.getAlumnos();
    }
    public LiveData<List<Alumno>> obtenerPorClase(int claseId) {
        return alumnoDao.getAlumnosPorClase(claseId);
    }
    public void insertar(Alumno alumno) {
        executor.execute(() -> alumnoDao.addAlumno(alumno));
    }
    public void eliminar(Alumno alumno) {
        executor.execute(() -> alumnoDao.deleteAlumno(alumno));
    }
    public void actualizar(Alumno alumno) {
        executor.execute(() -> alumnoDao.updateAlumno(alumno));
    }
    public LiveData<List<Clase>> obtenerClases() {
        return claseDao.obtenerClases();
    }
    public void insertarClase(Clase clase) {
        executor.execute(() -> claseDao.insert(clase));
    }
}