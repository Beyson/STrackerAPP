package com.example.ssis_tracker.view.notificaciones;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;

public class ImagenesNoticiasFragment extends Fragment {

    public String NotificacionImagenDoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_imagen_noticias, container , false);
        ImageView imageViewIMmagenNotificacion = view.findViewById(R.id.ImageViewNotificacion);

        Glide.with(view.getContext()).load(this.NotificacionImagenDoc).centerCrop().into(imageViewIMmagenNotificacion);
        return view;
    }
}
