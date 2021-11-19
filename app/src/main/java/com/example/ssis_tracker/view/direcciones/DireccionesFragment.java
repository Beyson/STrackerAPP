package com.example.ssis_tracker.view.direcciones;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.direcciones.AdapterDirecciones;
import com.example.ssis_tracker.model.Direccion;
import com.example.ssis_tracker.presenter.direcciones.DireccionesFragmentPresenter;
import com.example.ssis_tracker.presenter.direcciones.DireccionesFragmentPresenterImpl;
import java.util.ArrayList;

public class DireccionesFragment extends Fragment implements  DireccionesFragmentView{

    View view;
    AdapterDirecciones adapterDirecciones;
    DireccionesFragmentPresenter direccionesFragmentPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewDirecciones;
    private TextView textWithoutData;
    private MenuItem searchItem;
    private ArrayList<Direccion> direccionArrayList;
    public DireccionesFragment(){}


    @Override
    public void onResume() {
        super.onResume();
        getDirecciones();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        direccionesFragmentPresenter = new DireccionesFragmentPresenterImpl(this);
        view = inflater.inflate(R.layout.fragment_direcciones, container, false);
        textWithoutData = view.findViewById(R.id.textWithoutData);
        recyclerViewDirecciones = view.findViewById(R.id.recyclerViewDirecciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        adapterDirecciones = new AdapterDirecciones(view.getContext(), new ArrayList<Direccion>());
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        recyclerViewDirecciones.setAdapter(adapterDirecciones);
        recyclerViewDirecciones.setLayoutManager(linearLayoutManager);
        recyclerViewDirecciones.scheduleLayoutAnimation();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDirecciones();
            }
        });
        setHasOptionsMenu(true);

        configAppBar(false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.direcciones, menu);
        this.searchItem = menu.findItem(R.id.searchViewFind);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.searchItem.getIcon().setTint(Color.WHITE);
        }
        this.searchItem.setEnabled(true);
        this.searchItem.setVisible(true);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList <Direccion> listClone = new ArrayList<Direccion>();
                if(direccionArrayList != null || !s.equals("")) {
                    for (Direccion direccion : direccionArrayList) {
                        if (direccion.getNombre().toUpperCase().contains(s.toUpperCase())) {
                            listClone.add(direccion);
                        }
                    }
                    adapterDirecciones.adapterDataChange(listClone);
                }
                return false;
            }
        });
        searchView.setQueryHint("Buscar Dirección...");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        configAppBar(true);
    }

    @Override
    public void getDirecciones() {
        adapterDirecciones.adapterDataChange(new ArrayList<Direccion>());
        showSwipeRefreshLayout(true);
        direccionesFragmentPresenter.getDirecciones();
    }

    @Override
    public void showDirecciones(ArrayList<Direccion> direccionArrayList) {
        showSwipeRefreshLayout(false);
        adapterDirecciones.adapterDataChange(direccionArrayList);
        this.direccionArrayList = direccionArrayList;
        textWithoutData.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String strMessage) {
        Snackbar.make(view, strMessage, Snackbar.LENGTH_LONG).show();
        showSwipeRefreshLayout(false);
        textWithoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeRefreshLayout(boolean bool) {
        recyclerViewDirecciones.setVisibility(bool ? View.GONE :View.VISIBLE);
        swipeRefreshLayout.setRefreshing(bool);
    }

    @Override
    public void configAppBar(boolean bolDefault) {
        if(bolDefault)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("");
        else
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.direcciones);
    }
}
