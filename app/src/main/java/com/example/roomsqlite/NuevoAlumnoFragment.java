package com.example.roomsqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import viewmodel.AlumnoViewModel;

public class NuevoAlumnoFragment extends Fragment {
    private AlumnoViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo_alumno, container, false);

        EditText etNombre = view.findViewById(R.id.texto_nombre); // [cite: 280]
        EditText etNota = view.findViewById(R.id.texto_nota);     // [cite: 286]
        mViewModel = new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class);

        // Botón Guardar
        view.findViewById(R.id.boton_guardar).setOnClickListener(v -> { // [cite: 292]
            String nombre = etNombre.getText().toString();
            float nota = Float.parseFloat(etNota.getText().toString());

            Alumno nuevo = new Alumno(nombre, nota);
            mViewModel.insertar(nuevo); // Guarda en BD

            // Volver atrás
            Navigation.findNavController(view).popBackStack();
        });

        // Botón Cancelar
        view.findViewById(R.id.boton_cancelar).setOnClickListener(v -> { // [cite: 298]
            Navigation.findNavController(view).popBackStack();
        });

        return view;
    }
}