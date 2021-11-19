package com.example.ssis_tracker.adapter.direcciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Direccion;
import com.example.ssis_tracker.view.proyectos.ProyectosActivity;
import java.util.ArrayList;
import java.util.Random;

public class AdapterDirecciones extends RecyclerView.Adapter<AdapterDirecciones.HolderDirecciones> {

    private ArrayList<Direccion> arrayList;
    private Context context;
    private int lastPosition = -1;

    public AdapterDirecciones(Context context, ArrayList<Direccion> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderDirecciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_direcciones, viewGroup ,false);
        return new HolderDirecciones(view);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull HolderDirecciones holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDirecciones holderDirecciones, final int i) {
        Glide.with(this.context).load(R.drawable.ssis4).centerCrop().into(holderDirecciones.imageViewDireccion);

        holderDirecciones.textViewSiglas.setText(arrayList.get(i).getSiglas());
        holderDirecciones.textViewNombreDireccion.setText(arrayList.get(i).getNombre());
        holderDirecciones.textViewDirector.setText(arrayList.get(i).getDirector());
        holderDirecciones.textViewDescripcion.setText(arrayList.get(i).getDescripcion());

        /**Sección de la animación*/
        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.top_from_down : R.anim.down_from_top);
        holderDirecciones.itemView.startAnimation(animation);
        lastPosition = i;

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        Drawable drawable = holderDirecciones.relativeLayoutSiglas.getBackground();
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(color);

        /**Eventos*/
        holderDirecciones.cardViewDirecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProyectosActivity.class);
                intent.putExtra("direccion", arrayList.get(i).getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HolderDirecciones extends RecyclerView.ViewHolder {
        ImageView imageViewDireccion;
        TextView textViewSiglas;
        TextView textViewNombreDireccion;
        TextView textViewDirector;
        TextView textViewDescripcion;
        RelativeLayout relativeLayoutSiglas;
        CardView cardViewDirecciones;

        public HolderDirecciones(@NonNull View itemView) {
            super(itemView);
            imageViewDireccion = itemView.findViewById(R.id.imageViewDireccion);
            textViewSiglas = itemView.findViewById(R.id.textViewSiglas);
            textViewNombreDireccion = itemView.findViewById(R.id.textViewNombreDireccion);
            textViewDirector = itemView.findViewById(R.id.textViewDirector);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            relativeLayoutSiglas = itemView.findViewById(R.id.relativeLayoutSiglas);
            cardViewDirecciones = itemView.findViewById(R.id.cardViewDirecciones);
        }
    }

    public void adapterDataChange(ArrayList<Direccion> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
}
