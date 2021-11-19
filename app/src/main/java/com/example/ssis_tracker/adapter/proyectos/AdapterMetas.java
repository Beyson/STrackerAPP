package com.example.ssis_tracker.adapter.proyectos;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Meta;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class AdapterMetas extends  RecyclerView.Adapter<AdapterMetas.HolderMetas>{
    private ArrayList<Meta> metaArrayList;


    public AdapterMetas(ArrayList<Meta> metaArrayList){
        this.metaArrayList = metaArrayList;
    }

    public class HolderMetas extends RecyclerView.ViewHolder{
        TextView textViewActividad;
        TextView textViewMetrica;
        View viewDivider;
        PieChart pieAvanceMetas;

        public HolderMetas(@NonNull View itemView) {
            super(itemView);
            textViewActividad = itemView.findViewById(R.id.textViewActividad);
            textViewMetrica = itemView.findViewById(R.id.textViewMetrica);
            viewDivider = itemView.findViewById(R.id.viewDivider);
            pieAvanceMetas = itemView.findViewById(R.id.pieMetasRealizas);
        }
    }

    @NonNull
    @Override
    public HolderMetas onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_metas, viewGroup ,false);
        return new HolderMetas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMetas holderMetas, int i) {
        holderMetas.textViewActividad.setText(metaArrayList.get(i).getActividad());
        holderMetas.textViewMetrica.setText(metaArrayList.get(i).getUnidad() +  " - " +  String.valueOf(metaArrayList.get(i).getProgramado()));
        generarChart(metaArrayList.get(i).getPorcentajeEjecutado(), holderMetas.pieAvanceMetas, 10f);
    }

    @Override
    public int getItemCount() {
        return metaArrayList.size();
    }

    public void adapterDataChange(ArrayList<Meta> arrayList){
        this.metaArrayList = arrayList;
        notifyDataSetChanged();
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

}
