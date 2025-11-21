package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.R;

import java.util.List;

import viewmodel.AlumnoViewModel;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> listaAlumnos;
    private AlumnoViewModel viewModel;

    public AlumnoAdapter(AlumnoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAlumnos(List<Alumno> alumnos) {
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

            holder.btnBorrar.setOnClickListener(v -> {
                showDeleteConfirmationDialog(v.getContext(), alumnoActual);
            });

            holder.btnModificar.setOnClickListener(v -> {
                alumnoActual.nombre = holder.etNombre.getText().toString();
                try {
                    alumnoActual.nota = Float.parseFloat(holder.etNota.getText().toString());
                    viewModel.actualizar(alumnoActual);
                    Toast.makeText(v.getContext(), "Alumno actualizado", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(v.getContext(), "Nota inválida", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showDeleteConfirmationDialog(Context context, Alumno alumno) {
        new AlertDialog.Builder(context)
                .setTitle("Confirmar Borrado")
                .setMessage("¿Está seguro de borrar a " + alumno.nombre + "?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Sí, Borrar", (dialog, which) -> {
                    viewModel.eliminar(alumno);
                    Toast.makeText(context, "Alumno eliminado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public int getItemCount() {
        return listaAlumnos != null ? listaAlumnos.size() : 0;
    }

    class AlumnoViewHolder extends RecyclerView.ViewHolder {
        EditText etNombre, etNota;
        Button btnModificar, btnBorrar;

        public AlumnoViewHolder(View itemView) {
            super(itemView);
            etNombre = itemView.findViewById(R.id.etNombre);
            etNota = itemView.findViewById(R.id.etNota);
            btnModificar = itemView.findViewById(R.id.btnModificar);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }
}