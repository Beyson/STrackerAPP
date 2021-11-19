package com.example.ssis_tracker.adapter.proyectos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Vibrator;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.api.proyectos.ApiAdapterProyectos;
import com.example.ssis_tracker.api.proyectos.ApiServiceProyectos;
import com.example.ssis_tracker.model.Meta;
import com.example.ssis_tracker.model.Proyecto;
import com.example.ssis_tracker.presenter.llamadosatencion.LlamadosAtencionPresenter;
import com.example.ssis_tracker.presenter.llamadosatencion.LlamadosAtencionPresenterImpl;
import com.example.ssis_tracker.view.LlamadosAtencion.LlamadosAtencionView;
import com.example.ssis_tracker.view.agenda.AgendarTemaActivity;
import com.example.ssis_tracker.view.procesos.ProcesosActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProyectos extends RecyclerView.Adapter<AdapterProyectos.HolderProyectos> implements LlamadosAtencionView {

    private ArrayList<Proyecto> arrayList;
    private final Context context;
    private int lastPosition = -1;
    private ApiAdapterProyectos adapterProyectos;
    private ApiServiceProyectos serviceProyectos;
    private AlertDialog alertDialog;
    private AdapterMetas adapterMetas;
    private LlamadosAtencionPresenter llamadosAtencionPresenter;
    private String Usuario;
    private String Rol;
    private int direccion;
    private int HolderPosicionLlamadoAtencion = 0;

    public AdapterProyectos(Context context, ArrayList<Proyecto> arrayList , String Usuario , String Rol , int direccion){
        this.context = context;
        this.arrayList = arrayList;
        this.llamadosAtencionPresenter = new LlamadosAtencionPresenterImpl(this);
        this.Usuario = Usuario;
        this.Rol = Rol;
        this.direccion = direccion;
        adapterProyectos = new ApiAdapterProyectos();
        serviceProyectos = adapterProyectos.getClientService();
    }

    @NonNull
    @Override
    public HolderProyectos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proyecto, viewGroup ,false);
        return new HolderProyectos(view);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AdapterProyectos.HolderProyectos holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderProyectos holderProyectos, final int i) {
        holderProyectos.textViewNombreProyecto.setText(arrayList.get(i).getNombre().toUpperCase());
        holderProyectos.textViewFechasProyecto.setText(arrayList.get(i).getFechas());
        holderProyectos.textViewDescripcionProyecto.setText(arrayList.get(i).getDescripcion());
        holderProyectos.textViewEstado.setText(arrayList.get(i).getEstado());
        holderProyectos.TextViewLlamadoContador.setText(String.valueOf(arrayList.get(i).getLlamados()));

        Drawable drawable = holderProyectos.viewEstadoColor.getBackground();
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(Color.parseColor(arrayList.get(i).getColor()));

        generarChart(arrayList.get(i).getPorcentajeProcesos(), holderProyectos.pieChartRealizado, 8f);
        generarChart(arrayList.get(i).getPorcentajeDias() > 100 ? 100: arrayList.get(i).getPorcentajeDias(), holderProyectos.pieChartDiasTranscurridos,8f);

       /**Sección de color y animación*/
        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.top_from_down : R.anim.down_from_top);
        holderProyectos.itemView.startAnimation(animation);
        lastPosition = i;

        /**Evento*/
        holderProyectos.linearLayoutPumpunear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                if(vibrator.hasVibrator()){
                    vibrator.vibrate(300);
                }
                HolderPosicionLlamadoAtencion = i;
                llamadosAtencionPresenter.EnviarLlamadosdeAtencion(String.valueOf( arrayList.get(i).getId() ) , Usuario , Rol);
            }
        });

        holderProyectos.imageViewMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPoppupMetas(arrayList.get(i).getId(), v);
            }
        });

        holderProyectos.cardViewProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProcesosActivity.class);
                intent.putExtra("proyecto", arrayList.get(i).getId());
                intent.putExtra("nombre", arrayList.get(i).getNombre());
                intent.putExtra("direccion", direccion);
                v.getContext().startActivity(intent);
            }
        });

        holderProyectos.linearLayoutAgendarTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , AgendarTemaActivity.class);
                intent.putExtra("proyecto",arrayList.get(i).getId());
                intent.putExtra("agendarDirector",0);
                intent.putExtra("TemaProyecto",arrayList.get(i).getNombre().toUpperCase());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void ShowLlamadosdeAtencion(String Llamados) {
        this.arrayList.get(HolderPosicionLlamadoAtencion).setLlamados(
                this.arrayList.get(HolderPosicionLlamadoAtencion).getLlamados() +1
        );
        notifyDataSetChanged();
    }

    public static class HolderProyectos extends RecyclerView.ViewHolder {

        TextView textViewNombreProyecto;
        TextView textViewFechasProyecto;
        TextView textViewDescripcionProyecto;
        View viewEstadoColor;
        TextView textViewEstado;
        TextView textViewActualizacionMetas;
        PieChart pieChartRealizado;
        PieChart pieChartDiasTranscurridos;
        CardView cardViewProyecto;
        LinearLayout linearLayoutPumpunear;
        LinearLayout linearLayoutAgendarTema;
        ImageView imageViewMetas;
        TextView TextViewLlamadoContador;

        public HolderProyectos(@NonNull View itemView) {
            super(itemView);
            this.cardViewProyecto = itemView.findViewById(R.id.cardViewProyecto);
            this.textViewNombreProyecto = itemView.findViewById(R.id.textViewNombreProyecto);
            this.textViewFechasProyecto = itemView.findViewById(R.id.textViewFechasProyecto);
            this.textViewDescripcionProyecto = itemView.findViewById(R.id.textViewDescripcionProyecto);
            this.viewEstadoColor = itemView.findViewById(R.id.viewEstadoColor);
            this.textViewEstado = itemView.findViewById(R.id.textViewEstado);
            this.pieChartRealizado = itemView.findViewById(R.id.pieChartRealizado);
            this.pieChartDiasTranscurridos = itemView.findViewById(R.id.pieChartDiasTranscurridos);
            this.linearLayoutPumpunear = itemView.findViewById(R.id.linearLayoutPumpunear);
            this.textViewActualizacionMetas = itemView.findViewById(R.id.textViewActualizacionMetas);
            this.imageViewMetas = itemView.findViewById(R.id.imageViewMetas);
            this.linearLayoutAgendarTema = itemView.findViewById(R.id.linearLayoutAgendarTema);
            this.TextViewLlamadoContador = itemView.findViewById(R.id.TextViewLlamadoContador);
        }
    }

    private void generarChart(float procentaje , PieChart pieChart , float size){
        ArrayList<PieEntry> circleyVals  = new ArrayList<>();
        ArrayList<Integer>  circleColors = new ArrayList<>();
        PieDataSet circleDataSet;
        PieData circleData;

        pieChart.getDescription().setText("");
        pieChart.setHoleRadius(80f);
        pieChart.setRotationEnabled(false);
        pieChart.animateXY(0, 1500);
        pieChart.setCenterText(procentaje+"%");
        pieChart.setCenterTextSize(size);
        pieChart.setTouchEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawEntryLabels(false);

        circleyVals.add(new PieEntry(procentaje,"Valx"));
        circleyVals.add(new PieEntry(100-procentaje,"Valy"));

        circleColors.add(ColorTemplate.MATERIAL_COLORS[2]);
        circleColors.add(Color.parseColor("#F2F2F2"));

        circleDataSet = new PieDataSet(circleyVals,"");
        circleDataSet.setColors(circleColors);
        circleDataSet.setDrawValues(false);
        circleData = new PieData(circleDataSet);

        pieChart.setData(circleData);
    }


    public void adapterDataChange(ArrayList<Proyecto> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    private void showPoppupMetas(int proyecto, final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View viewInflate = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_metas, null);

        RecyclerView recyclerViewMetas = viewInflate.findViewById(R.id.recyclerViewMetas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        adapterMetas = new AdapterMetas(new ArrayList<Meta>());
        recyclerViewMetas.setAdapter(adapterMetas);
        recyclerViewMetas.setLayoutManager(linearLayoutManager);

        getMetas(proyecto, viewInflate);

        builder.setView(viewInflate);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void getMetas(int proyecto, final View view){
        Call<ArrayList<Meta>> call = serviceProyectos.getMetas(proyecto);
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
