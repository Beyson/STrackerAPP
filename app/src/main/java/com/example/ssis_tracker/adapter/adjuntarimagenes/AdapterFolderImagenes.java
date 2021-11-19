package com.example.ssis_tracker.adapter.adjuntarimagenes;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.view.adjuntarimagenes.ActivityFolderImagenes;
import java.io.File;
import java.util.ArrayList;

public class AdapterFolderImagenes extends RecyclerView.Adapter<AdapterFolderImagenes.HolderFolder> {

    private ArrayList<String[]> FolderImagenes;
    private ActivityFolderImagenes activityFolderImagenes;
    private Context context;

    public AdapterFolderImagenes(Context contx , ActivityFolderImagenes activityFolderImagenes){
        this.FolderImagenes = new ArrayList<>();
        this.context = contx;
        this.activityFolderImagenes = activityFolderImagenes;
    }


    @NonNull
    @Override
    public HolderFolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagen_folder , viewGroup , false);
        return new HolderFolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFolder holderFolder, final int i) {
        holderFolder.TextVIewNombreFolder.setText(this.FolderImagenes.get(i)[1]);
        holderFolder.TextViewCantidadImagen.setText(this.FolderImagenes.get(i)[2]);
        Glide.with(this.context)
                .load(Uri.fromFile(new File(this.FolderImagenes.get(i)[0])))
                .into(holderFolder.ImageVIewFolderImagen);

        holderFolder.linearLayoutFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityFolderImagenes.BuscarImagenes(FolderImagenes.get(i)[0]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return FolderImagenes.size();
    }

    public void agregarFolderImagenes(ArrayList<String[]> Folder){
        this.FolderImagenes = Folder;
        notifyDataSetChanged();
    }

    public class HolderFolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayoutFolder;
        public ImageView ImageVIewFolderImagen;
        public TextView  TextVIewNombreFolder;
        public TextView  TextViewCantidadImagen;


        public HolderFolder(@NonNull View itemView) {
            super(itemView);

            this.linearLayoutFolder     = itemView.findViewById(R.id.linearLayoutFolder);
            this.ImageVIewFolderImagen  = itemView.findViewById(R.id.ImageVIewFolderImagen);
            this.TextViewCantidadImagen = itemView.findViewById(R.id.TextViewCantidadImagen);
            this.TextVIewNombreFolder   = itemView.findViewById(R.id.TextVIewNombreFolder);
        }
    }
}
