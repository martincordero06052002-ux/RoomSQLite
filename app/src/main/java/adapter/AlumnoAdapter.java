package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.R;

import java.util.List;

import viewmodel.AlumnoViewModel;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {
    private List<Alumno> listaAlumnos;
    private AlumnoViewModel viewModel; // Referencia para borrar/editar

    public AlumnoAdapter(AlumnoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAlumnos(List<Alumno> alumnos){
        this.listaAlumnos = alumnos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_alumno, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        if (listaAlumnos != null) {
            Alumno alumnoActual = listaAlumnos.get(position);
            holder.etNombre.setText(alumnoActual.nombre);
            holder.etNota.setText(String.valueOf(alumnoActual.nota));

            // Implementar lógica de botones del PDF [cite: 337, 347]
            holder.btnBorrar.setOnClickListener(v -> viewModel.eliminar(alumnoActual));

            holder.btnModificar.setOnClickListener(v -> {
                // Actualizar objeto con nuevos datos de los EditTexts
                alumnoActual.nombre = holder.etNombre.getText().toString();
                alumnoActual.nota = Float.parseFloat(holder.etNota.getText().toString());
                viewModel.actualizar(alumnoActual);
            });
        }
    }

    @Override
    public int getItemCount() { return listaAlumnos != null ? listaAlumnos.size() : 0; }

    class AlumnoViewHolder extends RecyclerView.ViewHolder {
        EditText etNombre, etNota;
        Button btnModificar, btnBorrar;

        public AlumnoViewHolder(View itemView) {
            super(itemView);
            etNombre = itemView.findViewById(R.id.etNombre); // [cite: 315]
            etNota = itemView.findViewById(R.id.etNota);     // [cite: 325]
            btnModificar = itemView.findViewById(R.id.btnModificar); // [cite: 338]
            btnBorrar = itemView.findViewById(R.id.btnBorrar);       // [cite: 347]
        }
    }
}