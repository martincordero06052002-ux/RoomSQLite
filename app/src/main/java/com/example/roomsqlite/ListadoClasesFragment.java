package com.example.roomsqlite;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.ClaseAdapter;
import viewmodel.AlumnoViewModel;

public class ListadoClasesFragment extends Fragment {

    private AlumnoViewModel mViewModel;
    private ClaseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_clases, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_clases);
        FloatingActionButton fab = view.findViewById(R.id.fab_add_clase);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ClaseAdapter(new ArrayList<>(), clase -> {
            Bundle bundle = new Bundle();
            bundle.putInt("CLASE_ID_KEY", clase.id);
            Navigation.findNavController(view).navigate(R.id.action_clases_to_alumnos, bundle);
        });
        recyclerView.setAdapter(adapter);

        mViewModel = new ViewModelProvider(requireActivity()).get(AlumnoViewModel.class);

        mViewModel.obtenerClases().observe(getViewLifecycleOwner(), clases -> {
            adapter.setClases(clases);
        });

        fab.setOnClickListener(v -> mostrarDialogoNuevaClase());

        return view;
    }

    private void mostrarDialogoNuevaClase() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Nueva Clase");

        final EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Nombre de la clase (ej. 2º DAM)");
        builder.setView(input);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nombreClase = input.getText().toString();
            if (!nombreClase.isEmpty()) {
                mViewModel.insertarClase(new Clase(nombreClase));
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}