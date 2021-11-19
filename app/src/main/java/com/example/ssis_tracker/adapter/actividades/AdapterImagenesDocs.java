package com.example.ssis_tracker.adapter.actividades;

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
import com.example.ssis_tracker.api.ConfigAPI;
import com.example.ssis_tracker.model.Actividad.documentos_adjuntos;
import com.example.ssis_tracker.view.imagenesdocs.ImagenesDocActivity;
import java.util.ArrayList;

public class AdapterImagenesDocs extends RecyclerView.Adapter<AdapterImagenesDocs.HolderImagenes> {

    private ArrayList<documentos_adjuntos> arrayListImagenes;
    private Context context;
    private ConfigAPI configAPI;
    private int Actividad;


    public AdapterImagenesDocs(Context cntx , int actividad , ArrayList<documentos_adjuntos> imagenes){
        this.context = cntx;
        this.configAPI = new ConfigAPI();
        this.Actividad = actividad;
        this.arrayListImagenes = imagenes;
    }

    @NonNull
    @Override
    public HolderImagenes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagenes, viewGroup , false);
        return new HolderImagenes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderImagenes holderImagenes, final int i) {
        Glide.with(this.context).load( this.configAPI.getURL()+"/actividad/documento/"+String.valueOf(this.Actividad)+"/"+ String.valueOf( arrayListImagenes.get(i).getId_documento() ) )
                .centerCrop().
                into(holderImagenes.ImageViewDocs);
        holderImagenes.ImageViewDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), ImagenesDocActivity.class);
                intent.putParcelableArrayListExtra("imagenes" , arrayListImagenes);
                intent.putExtra("positionImg",i);
                intent.putExtra("actividad", Actividad);
                intent.putExtra("titulo", "Documentos de la actividad");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListImagenes.size();
    }

    public class HolderImagenes extends RecyclerView.ViewHolder {

        ImageView ImageViewDocs;

        public HolderImagenes(@NonNull View itemView) {
            super(itemView);
            ImageViewDocs = itemView.findViewById(R.id.ImageViewDocs);
        }
    }
}
