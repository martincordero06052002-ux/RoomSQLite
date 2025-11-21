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

        EditText etNombre = view.findViewById(R.id.texto_nombre);
        EditText etNota = view.findViewById(R.id.texto_nota);
        mViewModel = new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class);

        // Botón Guardar
        view.findViewById(R.id.boton_guardar).setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            float nota = Float.parseFloat(etNota.getText().toString());

            Alumno nuevo = new Alumno(nombre, nota);
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