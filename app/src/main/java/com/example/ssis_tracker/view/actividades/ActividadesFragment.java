package com.example.ssis_tracker.view.actividades;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.actividades.AdapterActividadesDependientes;
import com.example.ssis_tracker.adapter.actividades.AdapterComentarios;
import com.example.ssis_tracker.adapter.actividades.AdapterImagenesDocs;
import com.example.ssis_tracker.adapter.proyectos.AdapterMetas;
import com.example.ssis_tracker.api.actividades.ApiAdapterActividades;
import com.example.ssis_tracker.api.actividades.ApiServiceActividades;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.ActividadesDependientes;
import com.example.ssis_tracker.model.Comentario;
import com.example.ssis_tracker.model.Meta;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenter;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenterImpl;
import com.example.ssis_tracker.view.agregarcomentarios.ActivityComentar;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesFragment extends Fragment implements ActividadesFragmentView {

    public  Actividad                       actividad;
    public  String                          posicion;
    private AdapterComentarios              adapterComentarios;
    private TextView                        textWithoutDataComentario;
    private TextView                        textWithoutDataImg;
    private RecyclerView                    rvComentarios;
    private RecyclerView                    RecyclerViewImagenes;
    private FloatingActionButton            FloatingActionButtonComentar;
    private ActividadesActivityPresenter    actividadesFragmentPresenter;
    private AdapterImagenesDocs             adapterImagenes;
    private AdapterMetas                    adapterMetas;
    private AdapterActividadesDependientes  adapterActividadesDependientes;
    private AlertDialog                     alertDialog;
    private ApiAdapterActividades           apiAdapterActividades;
    private ApiServiceActividades           apiServiceActividades;

    public ActividadesFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actividades , container , false);

        this.actividadesFragmentPresenter = new ActividadesActivityPresenterImpl(null , this);
        TextView textViewNombreActividad = view.findViewById(R.id.textViewNombreActividad);
        TextView textViewDescripcionActividad = view.findViewById(R.id.textViewDescripcionActividad);
        View viewEstadoColor = view.findViewById(R.id.viewEstadoColor);
        TextView textViewEstado = view.findViewById(R.id.textViewEstado);
        TextView textViewPosicion = view.findViewById(R.id.textViewPosicion);
        TextView textViewDireccion = view.findViewById(R.id.textViewDireccion);
        TextView textViewSiglas = view.findViewById(R.id.textViewSiglas);
        TextView textViewResponsable = view.findViewById(R.id.textViewResponsable);
        TextView textViewFechaInicioPrevista = view.findViewById(R.id.textViewFechaInicioPrevista);
        TextView textViewFechaFinPrevista = view.findViewById(R.id.textViewFechaFinPrevista);
        TextView textViewDiasHabilesPrevistos = view.findViewById(R.id.textViewDiasHabilesPrevistos);
        TextView textViewFechaInicioReal = view.findViewById(R.id.textViewFechaInicioReal);
        TextView textViewFechaFinReal = view.findViewById(R.id.textViewFechaFinReal);
        TextView textViewDiasHabilesReales = view.findViewById(R.id.textViewDiasHabilesReales);
        this.textWithoutDataComentario = view.findViewById(R.id.textWithoutDataComentario);
        this.textWithoutDataImg = view.findViewById(R.id.textWithoutDataImg);

        this.apiAdapterActividades = new ApiAdapterActividades();
        this.apiServiceActividades = apiAdapterActividades.getClientService();

        LinearLayout linearLayoutMetas = view.findViewById(R.id.linearLayoutMetas);
        LinearLayout linearLayoutDependencia = view.findViewById(R.id.linearLayoutDependencia);
        textViewNombreActividad.setText(actividad.getNombre());
        textViewDescripcionActividad.setText(actividad.getDescripcion());
        Drawable drawable = viewEstadoColor.getBackground();
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(Color.parseColor(actividad.getColor()));
        textViewEstado.setText(actividad.getEstado());
        textViewPosicion.setText(posicion);
        textViewDireccion.setText(actividad.getDireccion());
        textViewSiglas.setText(actividad.getSiglas());
        textViewResponsable.setText(actividad.getResponsable());
        textViewFechaInicioPrevista.setText(actividad.getFechaInicioPrevista());
        textViewFechaFinPrevista.setText(actividad.getFechaFinPrevista());
        textViewDiasHabilesPrevistos.setText(String.valueOf(actividad.getDiasHabilesPrevistos()));
        textViewFechaInicioReal.setText(actividad.getFechaInicioReal());
        textViewFechaFinReal.setText(actividad.getFechaFinReal());
        textViewDiasHabilesReales.setText(String.valueOf(actividad.getDiasHabilesReales()));

        linearLayoutMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPoppupMetas(actividad.getId() , v);
            }
        });

        linearLayoutDependencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPoppupActividadesDependientes(actividad.getId() , v);
            }
        });

        this.rvComentarios = view.findViewById(R.id.recyclerViewComentarios);
        this.RecyclerViewImagenes = view.findViewById(R.id.recyclerViewImagenes);

        this.adapterComentarios = new AdapterComentarios();
        this.adapterImagenes = new AdapterImagenesDocs(this.getContext() , actividad.getId() , actividad.getFiles());

        LinearLayoutManager linearLayoutManagerComentarios = new LinearLayoutManager(this.getContext());
        LinearLayoutManager linearLayoutManagerImagenes = new LinearLayoutManager(this.getContext());
        linearLayoutManagerImagenes.setOrientation(LinearLayoutManager.HORIZONTAL);

        this.rvComentarios.setLayoutManager(linearLayoutManagerComentarios);
        this.rvComentarios.setAdapter(adapterComentarios);

        this.RecyclerViewImagenes.setAdapter(adapterImagenes);
        this.RecyclerViewImagenes.setLayoutManager(linearLayoutManagerImagenes);

        getComentarios(actividad.getId());

        this.FloatingActionButtonComentar = view.findViewById(R.id.FloatingActionButtonComentar);
        this.FloatingActionButtonComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , ActivityComentar.class);
                intent.putExtra("actividad",actividad.getId());
                v.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void getComentarios(int actividad) {
        this.actividadesFragmentPresenter.getComentarios(actividad);
    }

    @Override
    public void showComentarios(ArrayList<Comentario> comentarios) {
        if(comentarios.size() != 0){
            textWithoutDataComentario.setVisibility(View.GONE);
            rvComentarios.setVisibility(View.VISIBLE);
            adapterComentarios.AgregarComentarios(comentarios);
        }else {
            textWithoutDataComentario.setVisibility(View.VISIBLE);
            rvComentarios.setVisibility(View.GONE);
        }

    }

    private void showPoppupMetas(int actividad, final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View viewInflate = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_metas, null);

        RecyclerView recyclerViewMetas = viewInflate.findViewById(R.id.recyclerViewMetas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapterMetas = new AdapterMetas(new ArrayList<Meta>());
        recyclerViewMetas.setAdapter(adapterMetas);
        recyclerViewMetas.setLayoutManager(linearLayoutManager);

        getMetas(actividad, viewInflate);

        builder.setView(viewInflate);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void showPoppupActividadesDependientes(int actividad, final View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View viewInflate = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_dependientes, null);

        RecyclerView recyclerViewDependencias = viewInflate.findViewById(R.id.recyclerViewdependientes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapterActividadesDependientes = new AdapterActividadesDependientes(new ArrayList<ActividadesDependientes>());
        recyclerViewDependencias.setAdapter(adapterActividadesDependientes);
        recyclerViewDependencias.setLayoutManager(linearLayoutManager);

        getActividadesDependientes(actividad, viewInflate);

        builder.setView(viewInflate);
        alertDialog = builder.create();
        alertDialog.show();
    }


    private void getActividadesDependientes(final int actividad, final View view){
        Call<ArrayList<ActividadesDependientes>> call = apiServiceActividades.getActividadeDependientes(actividad);
        call.enqueue(new Callback<ArrayList<ActividadesDependientes>>() {
            @Override
            public void onResponse(Call<ArrayList<ActividadesDependientes>> call, Response<ArrayList<ActividadesDependientes>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                      adapterActividadesDependientes.adapterDataChange(response.body());
                }else{
                    Snackbar.make(view, "No se detectaron actividades dependientes.", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ActividadesDependientes>> call, Throwable t) {

            }
        });
    }

    private void getMetas(int actividad, final View view){
        Call<ArrayList<Meta>> call = apiServiceActividades.getMetas(actividad);
        call.enqueue(new Callback<ArrayList<Meta>>() {
            @Override
            public void onResponse(Call<ArrayList<Meta>> call, Response<ArrayList<Meta>> response) {
                if(response.isSuccessful() && !response.body().isEmpty()){
                    adapterMetas.adapterDataChange(response.body());
                }else{
                    Snackbar.make(view, "No se detectaron metas.", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Meta>> call, Throwable t) {
                Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
