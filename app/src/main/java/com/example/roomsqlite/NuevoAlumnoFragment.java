package com.example.roomsqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.List;

import viewmodel.AlumnoViewModel;

public class NuevoAlumnoFragment extends Fragment {

    private AlumnoViewModel mViewModel;
    private Spinner spinnerClases;
    private Clase claseSeleccionada;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo_alumno, container, false);

        EditText etNombre = view.findViewById(R.id.texto_nombre);
        EditText etNota = view.findViewById(R.id.texto_nota);
        spinnerClases = view.findViewById(R.id.spinner_clases);

        mViewModel = new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class);

        mViewModel.obtenerClases().observe(getViewLifecycleOwner(), new Observer<List<Clase>>() {
            @Override
            public void onChanged(List<Clase> clases) {
                ArrayAdapter<Clase> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, clases);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerClases.setAdapter(adapter);
            }
        });

        spinnerClases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                claseSeleccionada = (Clase) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                claseSeleccionada = null;
            }
        });

        view.findViewById(R.id.boton_guardar).setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String notaStr = etNota.getText().toString();

            if (nombre.isEmpty() || notaStr.isEmpty()) {
                Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación: Obligatorio seleccionar clase
            if (claseSeleccionada == null) {
                Toast.makeText(getContext(), "Debes crear y seleccionar una clase primero", Toast.LENGTH_LONG).show();
                return;
            }

            float nota = Float.parseFloat(notaStr);

            // Crear alumno y asignar la ID de la clase
            Alumno nuevo = new Alumno(nombre, nota);
            nuevo.claseId = claseSeleccionada.id; // ASIGNACIÓN CLAVE

            mViewModel.insertar(nuevo);

            // Volver atrás
            Navigation.findNavController(view).popBackStack();
        });

        // Botón Cancelar
        view.findViewById(R.id.boton_cancelar).setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        return view;
    }
}