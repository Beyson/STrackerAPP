package com.example.ssis_tracker.view.notificaciones;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.noticias.AdapterNoticias;

public class NoticiasFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container   ,
                             @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_noticias , container , false);
        RecyclerView recyclerViewNotificaciones = this.view.findViewById(R.id.recyclerViewNotificaciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        AdapterNoticias adapterNotificaciones   = new AdapterNoticias(this.getContext() );

        recyclerViewNotificaciones.setLayoutManager(linearLayoutManager);
        recyclerViewNotificaciones.setAdapter(adapterNotificaciones);
        return this.view;
    }
}
