package com.example.ssis_tracker.view.busquedas;

import android.annotation.SuppressLint;
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
import com.example.ssis_tracker.adapter.busquedas.AdapterBusqueda;
import com.example.ssis_tracker.model.Searched;
import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class EncontradosFragment extends Fragment {
    private RecyclerView rvEncontrados;
    private View view;
    private ArrayList<Searched> arrayListEncontrados;

    public  EncontradosFragment(ArrayList<Searched> arrayListEncontrados){
        this.arrayListEncontrados=arrayListEncontrados;
    }
    public EncontradosFragment(){
        arrayListEncontrados= new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view          = inflater.inflate(R.layout.layout_busquedas, container, false);
        rvEncontrados = view.findViewById(R.id.rvEncontrados);

        AdapterBusqueda adapterBusqueda = new AdapterBusqueda(getContext(),arrayListEncontrados);
        rvEncontrados.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEncontrados.setAdapter(adapterBusqueda);
        return view;
    }
}
