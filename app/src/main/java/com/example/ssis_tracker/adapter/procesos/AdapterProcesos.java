package com.example.ssis_tracker.adapter.procesos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.proyectos.AdapterMetas;
import com.example.ssis_tracker.api.procesos.ApiAdapterProcesos;
import com.example.ssis_tracker.api.procesos.ApiServiceProcesos;
import com.example.ssis_tracker.model.Meta;
import com.example.ssis_tracker.model.Proceso;
import com.example.ssis_tracker.view.actividades.ActividadesActivity;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProcesos extends RecyclerView.Adapter<AdapterProcesos.HolderProcess>{
    private ArrayList<Proceso> procesoArrayList;
    private Context context;
    private int lastPosition = -1;
    private ApiAdapterProcesos adapterProcesos;
    private ApiServiceProcesos serviceProcesos;
    private AlertDialog alertDialog;
    private AdapterMetas adapterMetas;
    private int direccion;

    public AdapterProcesos(ArrayList<Proceso> procesoArrayList, Context context , int direccion){
        this.procesoArrayList = procesoArrayList;
        this.context = context;
        this.direccion = direccion;
        adapterProcesos = new ApiAdapterProcesos();
        serviceProcesos = adapterProcesos.getClientService();
    }

    public class HolderProcess extends RecyclerView.ViewHolder{
        private TextView textViewNombreProceso;
        private TextView textViewDescripcion;
        private TextView textViewNumeroProceso;
        private CardView cardViewItem;
        private ImageButton imageButtonMetas;
        private TextView textViewEstado;

        public HolderProcess(View itemView){
            super(itemView);
            textViewNombreProceso = itemView.findViewById(R.id.textViewNombreProceso);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            textViewNumeroProceso = itemView.findViewById(R.id.textViewNumeroProceso);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);
            imageButtonMetas = itemView.findViewById(R.id.imageButtonMetas);
            textViewEstado = itemView.findViewById(R.id.textViewEstado);
        }
    }

    @NonNull
    @Override
    public AdapterProcesos.HolderProcess onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_proceso, viewGroup, false);
        return new AdapterProcesos.HolderProcess(view);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AdapterProcesos.HolderProcess holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProcesos.HolderProcess viewHolder, final int i) {
        int counter = i + 1;
        String numeroProceso = counter + "/" + getItemCount();

        viewHolder.textViewNombreProceso.setText(procesoArrayList.get(i).getNombre());
        viewHolder.textViewDescripcion.setText(procesoArrayList.get(i).getDescripcion());
        viewHolder.textViewEstado.setText(procesoArrayList.get(i).getEstado());
        viewHolder.textViewNumeroProceso.setText(numeroProceso);

        /**Seccion animación */
        Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.top_from_down : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = i;

        Drawable drawable = context.getResources().getDrawable(R.drawable.drw_acronym);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(Color.parseColor((procesoArrayList.get(i).getColor())));

        viewHolder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , ActividadesActivity.class);
                intent.putExtra("proceso", procesoArrayList.get(i).getId());
                intent.putExtra("nombre", procesoArrayList.get(i).getNombre());
                intent.putExtra("direccion", direccion);
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.imageButtonMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPoppupMetas(procesoArrayList.get(i).getId(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return procesoArrayList.size();
    }

    public void adapterDataChange(ArrayList<Proceso> procesoArrayList){
        this.procesoArrayList = procesoArrayList;
        notifyDataSetChanged();
    }

    private void showPoppupMetas(int proceso, final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View viewInflate = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_metas, null);

        RecyclerView recyclerViewMetas = viewInflate.findViewById(R.id.recyclerViewMetas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        adapterMetas = new AdapterMetas(new ArrayList<Meta>());
        recyclerViewMetas.setAdapter(adapterMetas);
        recyclerViewMetas.setLayoutManager(linearLayoutManager);

        getMetas(proceso, viewInflate);

        builder.setView(viewInflate);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void getMetas(int proceso, final View view){
        Call<ArrayList<Meta>> call = serviceProcesos.getMetas(proceso);
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
