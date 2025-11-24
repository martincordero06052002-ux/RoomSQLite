package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomsqlite.Clase;
import com.example.roomsqlite.R;

import java.util.List;

public class ClaseAdapter extends RecyclerView.Adapter<ClaseAdapter.ClaseViewHolder> {

    private List<Clase> clases;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Clase clase);
    }

    public ClaseAdapter(List<Clase> clases, OnItemClickListener listener) {
        this.clases = clases;
        this.listener = listener;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clase, parent, false);
        return new ClaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaseViewHolder holder, int position) {
        Clase clase = clases.get(position);
        holder.nombre.setText(clase.nombre);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(clase));
    }

    @Override
    public int getItemCount() {
        return clases != null ? clases.size() : 0;
    }

    static class ClaseViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        public ClaseViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_clase);
        }
    }
}