package com.example.ssis_tracker.view.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.agenda.AdapterAgenda;
import com.example.ssis_tracker.model.Agenda;
import com.example.ssis_tracker.presenter.agenda.AgendaFragmentPresenter;
import com.example.ssis_tracker.presenter.agenda.AgendaFragmentPresenterImpl;
import java.util.ArrayList;

public class AgendaFragment  extends Fragment implements AgendaFragmentView {

    private View view;
    private RecyclerView rvAgenda;
    private SwipeRefreshLayout swipeRefreshLayoutAgenda;
    private AdapterAgenda adapterAgenda;
    private AgendaFragmentPresenter agendaFragmentPresenter;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_agenda, container, false);
        super.onCreate(savedInstanceState);
        this.rvAgenda = this.view.findViewById(R.id.rvAgenda);
        this.swipeRefreshLayoutAgenda = this.view.findViewById(R.id.swipeRefreshLayoutAgenda);
        this.agendaFragmentPresenter = new AgendaFragmentPresenterImpl(this);
        this.sharedPreferences = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);

        this.adapterAgenda = new AdapterAgenda(getContext(),this.sharedPreferences.getInt("id_rol",0));
        this.rvAgenda.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvAgenda.setAdapter(adapterAgenda);

        FloatingActionButton FloatActionButton = view.findViewById(R.id.FloatingActionButtonAgendar);
        FloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgendar  = new Intent(v.getContext(),AgendarTemaActivity.class);
                v.getContext().startActivity(intentAgendar);
            }
        });

        this.swipeRefreshLayoutAgenda.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetTemasAgendados();
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Agendar");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.swipeRefreshLayoutAgenda.setRefreshing(true);
        GetTemasAgendados();
    }

    @Override
    public void configAppBar(boolean bolDefault) {
        if(bolDefault)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("");
        else
            ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Agenda Staff");
    }


    @Override
    public void GetTemasAgendados() {
        this.agendaFragmentPresenter.GetTemasAgendados();
    }

    @Override
    public void ListarTemasAgendados(ArrayList<Agenda> TemasAgendados) {
        this.swipeRefreshLayoutAgenda.setRefreshing(false);
        adapterAgenda.AgregarTemasAgendados(TemasAgendados);
    }


    @Override
    public void EliminarTemasAgendados(int IdTema , int idRol) {
        if(this.agendaFragmentPresenter == null )
            this.agendaFragmentPresenter = new AgendaFragmentPresenterImpl(this);
        this.agendaFragmentPresenter.EliminarTemasAgendados(IdTema,idRol);
    }

    @Override
    public void ErrorListarTemas(String TxtError) {
        this.swipeRefreshLayoutAgenda.setRefreshing(false);
        Snackbar.make(this.view , TxtError, Snackbar.LENGTH_LONG).show();
    }
}
