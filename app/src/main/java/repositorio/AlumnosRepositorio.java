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
    private Executor executor = Executors.newSingleThreadExecutor(); // [cite: 151]

    public AlumnosRepositorio(Application application) {
        alumnoDao = AlumnoDataBase.obtenerInstancia(application).getAlumnoDao(); // [cite: 153]
    }

    public LiveData<List<Alumno>> obtener() {
        return alumnoDao.getAlumnos(); // [cite: 160]
    }

    public void insertar(Alumno alumno) {
        executor.execute(() -> alumnoDao.addAlumno(alumno)); // [cite: 166-169]
    }

    public void eliminar(Alumno alumno) {
        executor.execute(() -> alumnoDao.deleteAlumno(alumno)); // [cite: 173-180]
    }

    public void actualizar(Alumno alumno) { // Modificado para recibir objeto completo
        executor.execute(() -> alumnoDao.updateAlumno(alumno)); // [cite: 181-187]
    }
}