package com.example.ssis_tracker.view.imagenesdocs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.ssis_tracker.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ImagenesDocsFragment extends Fragment {

    public static ImagenesDocsFragment NuevaInstancia(String imgUrl){
        ImagenesDocsFragment imageDocsFragment = new ImagenesDocsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imgUrl",imgUrl);
        imageDocsFragment.setArguments(bundle);
        return  imageDocsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View imageDocsFragment   = inflater.inflate(R.layout.fragment_imgdocs , container , false);
        PhotoView PhotoViewImagenDoc = imageDocsFragment.findViewById(R.id.PhotoViewImagenDoc);
        Glide.with(this.getContext()).load(getArguments().getString("imgUrl")).centerCrop().into(PhotoViewImagenDoc);
        return  imageDocsFragment;
    }
}
