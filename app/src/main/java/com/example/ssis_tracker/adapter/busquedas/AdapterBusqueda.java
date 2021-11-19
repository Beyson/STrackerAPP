package com.example.ssis_tracker.adapter.busquedas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Searched;
import com.example.ssis_tracker.view.actividades.ActividadesActivity;
import com.example.ssis_tracker.view.procesos.ProcesosActivity;
import com.example.ssis_tracker.view.proyectos.ProyectosActivity;

import java.util.ArrayList;

public class AdapterBusqueda extends RecyclerView.Adapter<AdapterBusqueda.HolderSearched>{
    private ArrayList<Searched> arrayListEncontrados;
    private Context context;
    private int lastPosition = -1;

    public class HolderSearched extends RecyclerView.ViewHolder{
        private TextView tvTituloItem;
        private TextView tvDescripcionItem;
        private TextView tvState;
        private ImageView ivIconState;
        private CardView cardViewItem;

        public HolderSearched(@NonNull View itemView){
            super(itemView);
            this.tvTituloItem = itemView.findViewById(R.id.tvTituloItem);
            this.tvDescripcionItem=itemView.findViewById(R.id.tvDescripcionItem);
            this.ivIconState    = itemView.findViewById(R.id.ivIconState);
            this.tvState        = itemView.findViewById(R.id.tvState);
            this.cardViewItem   = itemView.findViewById(R.id.cardViewItem);
        }
    }

    public AdapterBusqueda(Context context, ArrayList<Searched> arrayListEncontrados){
        this.arrayListEncontrados=arrayListEncontrados;
        this.context=context;
    }

    @Override
    public HolderSearched onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_busqueda, viewGroup, false);
        return new AdapterBusqueda.HolderSearched(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBusqueda.HolderSearched holderSearched, final int i) {
        final Searched searched = arrayListEncontrados.get(i);
        holderSearched.tvTituloItem.setText(searched.getTitulo());
        holderSearched.tvDescripcionItem.setText(searched.getDescripcion());
        holderSearched.tvState.setText(searched.getEstado());
        holderSearched.ivIconState.setColorFilter(Color.parseColor(searched.getColor()));

        holderSearched.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(searched.getTipo() == 0){ //Si el cardview seleccionado pertenece a un proyecto
                    Intent intent = new Intent(v.getContext(), ProyectosActivity.class);
                    intent.putExtra("cod_proyecto",searched.getId());
                    v.getContext().startActivity(intent);
                }
                if(searched.getTipo() == 1){  //Si el cardview seleccionado pertenece a un proceso
                    Intent intent = new Intent(v.getContext(), ProcesosActivity.class);
                    intent.putExtra("cod_proceso",searched.getId());
                    v.getContext().startActivity(intent);
                }
                if(searched.getTipo() == 2){  //Si el cardview seleccionado pertenece a una actividad
                    Intent intent = new Intent(v.getContext(), ActividadesActivity.class);
                    intent.putExtra("cod_actividad",searched.getId());
                    v.getContext().startActivity(intent);
                }

            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.top_from_down : R.anim.down_from_top);
        holderSearched.itemView.startAnimation(animation);
        lastPosition = i;
    }

    @Override
    public int getItemCount() {
        return arrayListEncontrados.size();
    }


}
