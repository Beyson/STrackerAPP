package com.example.ssis_tracker.adapter.actividades;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.ActividadesDependientes;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;


public class AdapterActividadesDependientes extends RecyclerView.Adapter<AdapterActividadesDependientes.HolderDependientes> {

    private ArrayList<ActividadesDependientes> arrayListDepondientes;

    public AdapterActividadesDependientes(ArrayList<ActividadesDependientes> arrayListDepondientes){
        this.arrayListDepondientes = arrayListDepondientes;
    }

    @NonNull
    @Override
    public HolderDependientes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dependientes , viewGroup ,false);
        return new HolderDependientes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDependientes holderDependientes, int i) {
        holderDependientes.textViewProceso.setText(this.arrayListDepondientes.get(i).getProceso());
        holderDependientes.textViewUnidad.setText(this.arrayListDepondientes.get(i).getUnidad_asignada());
        holderDependientes.textViewActividadDependiente.setText(this.arrayListDepondientes.get(i).getActividad());
        generarChart(this.arrayListDepondientes.get(i).getPorcentaje_ejecutado() , holderDependientes.pieActividadesDependientes , 10f);
    }

    public void adapterDataChange(ArrayList<ActividadesDependientes> arrayListDepondientes){
        this.arrayListDepondientes = arrayListDepondientes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.arrayListDepondientes.size();
    }

    public class HolderDependientes extends RecyclerView.ViewHolder {

        PieChart pieActividadesDependientes;
        TextView textViewActividadDependiente;
        TextView textViewProceso;
        TextView textViewUnidad;

        public HolderDependientes(@NonNull View itemView) {
            super(itemView);

            this.pieActividadesDependientes = itemView.findViewById(R.id.pieActividadesDependientesRealizas);
            this.textViewActividadDependiente = itemView.findViewById(R.id.textViewActividadDependiente);
            this.textViewProceso = itemView.findViewById(R.id.textViewProceso);
            this.textViewUnidad = itemView.findViewById(R.id.textViewUnidad);
        }
    }

    private void generarChart(int procentaje , PieChart pieChart , float size){
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


}


