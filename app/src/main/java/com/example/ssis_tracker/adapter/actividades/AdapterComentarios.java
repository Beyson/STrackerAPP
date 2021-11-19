package com.example.ssis_tracker.adapter.actividades;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Comentario;

import java.util.ArrayList;


public class AdapterComentarios extends RecyclerView.Adapter<AdapterComentarios.HolderComentarios> {

    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public AdapterComentarios(){
    }

    @NonNull
    @Override
    public HolderComentarios onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comentarios, viewGroup, false);
        return new HolderComentarios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComentarios holderComentarios, int i) {
        holderComentarios.txtUsuario.setText(comentarios.get(i).getUsuario());
        holderComentarios.txtFchActualizacion.setText(comentarios.get(i).getFechaComentario());
        holderComentarios.txtComentario.setText(comentarios.get(i).getComentario());
    }


    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public void AgregarComentarios(ArrayList<Comentario> comentarios){
        this.comentarios = comentarios;
        notifyDataSetChanged();
    }

    public class HolderComentarios extends RecyclerView.ViewHolder {

        private TextView     txtUsuario;
        private TextView     txtFchActualizacion;
        private TextView     txtComentario;

        public HolderComentarios(@NonNull View itemView) {
            super(itemView);

            txtUsuario  = itemView.findViewById(R.id.txt_UsuarioNombre);
            txtFchActualizacion = itemView.findViewById(R.id.txt_fchComentario);
            txtComentario = itemView.findViewById(R.id.txtComentario);

        }
    }
}
