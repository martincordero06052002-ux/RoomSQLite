package com.example.roomsqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomsqlite.Alumno;
import com.example.roomsqlite.R;

import java.util.List;

import adapter.AlumnoAdapter;
import viewmodel.AlumnoViewModel;

public class ListadoAlumnosFragment extends Fragment {
    private AlumnoViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_alumnos, container, false);

        // Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView); // [cite: 257]
        final AlumnoAdapter adapter = new AlumnoAdapter(new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // [cite: 260]
        recyclerView.setAdapter(adapter);

        // Configurar ViewModel
        mViewModel = new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class);
        mViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Alumno>>() {
            @Override
            public void onChanged(List<Alumno> alumnos) {
                // Actualiza el adaptador cuando cambia la BD [cite: 114]
                adapter.setAlumnos(alumnos);
            }
        });

        // Botón flotante para ir a crear nuevo alumno
        view.findViewById(R.id.irANuevoAlumno).setOnClickListener(v -> { // [cite: 262]
            Navigation.findNavController(view).navigate(R.id.action_listado_to_nuevo);
        });

        return view;
    }
}