package com.example.ssis_tracker.adapter.noticias;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.view.imagenesdocs.ImagenesDocActivity;
import java.util.ArrayList;

public class AdapterImagenesNoticias extends RecyclerView.Adapter<AdapterImagenesNoticias.HolderImagenesComentario> {

    private ArrayList<Actividad.documentos_adjuntos> ImagenNotificacion;
    private Context context;

    public AdapterImagenesNoticias(Context context){
        this.ImagenNotificacion = new ArrayList<>();
        this.context = context;
    }

    public void AgregarImagen(Actividad.documentos_adjuntos Imagen){
        this.ImagenNotificacion.add(Imagen);
    }

    @NonNull
    @Override
    public HolderImagenesComentario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagen_noticias,viewGroup , false);
        return new HolderImagenesComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderImagenesComentario holderImagenesComentario, final int i) {
        Glide.with(this.context).load(this.ImagenNotificacion.get(i)).centerCrop().into(holderImagenesComentario.ImagenNotificacion);
        holderImagenesComentario.ImagenNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), ImagenesDocActivity.class);
                intent.putParcelableArrayListExtra("imagenes",ImagenNotificacion);
                intent.putExtra("positionImg",i);
                intent.putExtra("titulo", "Imagenes de la noticia");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.ImagenNotificacion.size();
    }

    public class HolderImagenesComentario extends RecyclerView.ViewHolder {

        private ImageView ImagenNotificacion;

        public HolderImagenesComentario(@NonNull View itemView) {
            super(itemView);
            this.ImagenNotificacion = itemView.findViewById(R.id.ImageViewNotificacion);
        }
    }
}
