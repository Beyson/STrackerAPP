package com.example.ssis_tracker.adapter.crearnoticias;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import java.util.ArrayList;

public class adapterImagenNoticia extends RecyclerView.Adapter<adapterImagenNoticia.HolderImagenesNoticias> {

    private ArrayList<String[]> ArrayImagenesNoticia;
    private Context context;

    public adapterImagenNoticia(ArrayList<String[]> arrayImagenesNoticia , Context context){
        this.ArrayImagenesNoticia = arrayImagenesNoticia;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderImagenesNoticias onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagenes,viewGroup, false);
        return new HolderImagenesNoticias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderImagenesNoticias holderImagenesNoticias, final int i) {
        holderImagenesNoticias.FrameLayoutClose.setVisibility(View.VISIBLE);
        Glide.with(this.context)
             .load(this.ArrayImagenesNoticia.get(i)[0])
             .centerCrop()
             .into(holderImagenesNoticias.ImageViewDocs);

        holderImagenesNoticias.FrameLayoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayImagenesNoticia.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.ArrayImagenesNoticia.size();
    }

    public void AgregarImagenes(ArrayList<String[]> Imagenesnoticia){
        this.ArrayImagenesNoticia = Imagenesnoticia;
        notifyDataSetChanged();
    }

    public class HolderImagenesNoticias extends RecyclerView.ViewHolder {

        public ImageView ImageViewDocs;
        private FrameLayout FrameLayoutClose;

        public HolderImagenesNoticias(@NonNull View itemView) {
            super(itemView);
            this.ImageViewDocs    = itemView.findViewById(R.id.ImageViewDocs);
            this.FrameLayoutClose = itemView.findViewById(R.id.FrameLayoutClose);
        }
    }
}
