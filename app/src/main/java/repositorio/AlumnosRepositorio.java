package repositorio;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.AlumnoDao;
import com.example.roomsqlite.AlumnoDataBase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AlumnosRepositorio {
    private AlumnoDao alumnoDao;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AlumnosRepositorio(Application application) {
        alumnoDao = AlumnoDataBase.obtenerInstancia(application).getAlumnoDao();
    }

    public LiveData<List<Alumno>> obtener() {
        return alumnoDao.getAlumnos();
    }

    public void insertar(Alumno alumno) {
        executor.execute(() -> alumnoDao.addAlumno(alumno));
    }

    public void eliminar(Alumno alumno) {
        executor.execute(() -> alumnoDao.deleteAlumno(alumno));
    }

    public void actualizar(Alumno alumno) { // Modificado para recibir objeto completo
        executor.execute(() -> alumnoDao.updateAlumno(alumno));
    }
}