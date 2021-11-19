package com.example.ssis_tracker.adapter.adjuntarimagenes;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.view.crearnoticia.CrearNoticiaActivity;

import java.io.File;
import java.util.ArrayList;

public class AdapterImagenesAdjuntar extends RecyclerView.Adapter<AdapterImagenesAdjuntar.HolderImagenes> {

    private ArrayList<String[]> ImagenesAdjuntar;
    private ArrayList<String[]> ImagenesSeleccionados;
    private Context context;

    public AdapterImagenesAdjuntar(Context context){
        this.ImagenesAdjuntar = new ArrayList<>();
        this.ImagenesSeleccionados = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public HolderImagenes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view   = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagen_en_carpeta , viewGroup ,false);
        return new HolderImagenes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderImagenes holderImagenes, final int i) {

        Glide.with(this.context)
                .load(new File(this.ImagenesAdjuntar.get(i)[0]))
                .into(holderImagenes.ImageViewImagenAdjuntar);

        for(int x = 0; x < this.ImagenesSeleccionados.size(); x++){
            if(this.ImagenesSeleccionados.get(x)[1].equals(String.valueOf(i))){
                holderImagenes.ToggleButtonSeleccion.setChecked(true);
                break;
            }else{
                holderImagenes.ToggleButtonSeleccion.setChecked(false);
            }
        }

        holderImagenes.linearLayoutImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagenSeleccionada(i , holderImagenes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ImagenesAdjuntar.size();
    }

    public void AgregarImagenesAdapter(ArrayList<String[]> arrImagenes){
        this.ImagenesAdjuntar = arrImagenes;
        notifyDataSetChanged();
    }

    private void ImagenSeleccionada(int posicion, HolderImagenes holderImagenes){
        boolean encontrado = false;
        for(int i = 0; i < this.ImagenesSeleccionados.size(); i++){
            if(String.valueOf(posicion).equals(  this.ImagenesSeleccionados.get(i)[1] )){
                encontrado = true;
                this.ImagenesSeleccionados.remove(i);
                holderImagenes.ToggleButtonSeleccion.setChecked(false);
                break;
            }
        }

        if(!encontrado){
            holderImagenes.ToggleButtonSeleccion.setChecked(true);
            this.ImagenesSeleccionados.add(new String[]{ this.ImagenesAdjuntar.get(posicion)[0], String.valueOf(posicion) , "1"});
        }
    }

    public void ImagenesSeleccionadas(){
        CrearNoticiaActivity.setImagenesAdjuntas(this.ImagenesSeleccionados);
        Intent intent = new Intent(this.context , CrearNoticiaActivity.class );
        this.context.startActivity(intent);
    }

    public class HolderImagenes extends RecyclerView.ViewHolder {

        public LinearLayout linearLayoutImagen;
        public ImageView    ImageViewImagenAdjuntar;
        public ToggleButton ToggleButtonSeleccion;

        public HolderImagenes(@NonNull View itemView) {
            super(itemView);
            this.linearLayoutImagen = itemView.findViewById(R.id.linearLayoutImagen);
            this.ImageViewImagenAdjuntar = itemView.findViewById(R.id.ImageViewImagenAdjuntar);
            this.ToggleButtonSeleccion = itemView.findViewById(R.id.ToggleButtonSeleccion);
        }
    }
}
