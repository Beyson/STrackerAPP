package com.example.ssis_tracker.adapter.agenda;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Agenda;
import com.example.ssis_tracker.view.agenda.AgendaFragment;
import java.util.ArrayList;

public class AdapterAgenda extends RecyclerSwipeAdapter<AdapterAgenda.HolderAgenda> {

    private ArrayList<Agenda> arrayListAgenda;
    private AdapterAgenda.OnItemClickListener onClickListener;
    private Context context;
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);
    private int lastPosition = -1;
    private int RolId = 0;

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    public class HolderAgenda extends RecyclerView.ViewHolder{
        private TextView textViewTituloAgenda;
        private TextView textViewFechaAgenda;
        private TextView textViewDescripcion;
        private SwipeLayout swipeLayout;
        private LinearLayout lytDelete;

        public HolderAgenda(@NonNull View itemView, AdapterAgenda.OnItemClickListener listener) {
            super(itemView);

            this.textViewTituloAgenda   = itemView.findViewById(R.id.textViewTituloAgenda);
            this.textViewFechaAgenda    = itemView.findViewById(R.id.textViewFechaAgenda);
            this.textViewDescripcion    = itemView.findViewById(R.id.textViewDescripcion);

            this.swipeLayout            = itemView.findViewById(R.id.swipeLayout);
            this.lytDelete              = itemView.findViewById(R.id.lytdelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        if(getAdapterPosition() != RecyclerView.NO_POSITION){
                            onClickListener.onitemClick(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }

    public AdapterAgenda(Context context , int idRol){
        this.arrayListAgenda = new ArrayList<>();
        this.context= context;
        this.RolId = idRol;
    }

    public interface OnItemClickListener{
        void onitemClick(int position);
    }

    public void setOnClickListener(AdapterAgenda.OnItemClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AdapterAgenda.HolderAgenda holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }


    public void AgregarTemasAgendados(ArrayList<Agenda> arrayListAgenda){
        this.arrayListAgenda=arrayListAgenda;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterAgenda.HolderAgenda onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agenda, viewGroup, false);
        return new AdapterAgenda.HolderAgenda(view, this.onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterAgenda.HolderAgenda viewHolder, final int i) {
        final Agenda agenda = arrayListAgenda.get(i);
        viewHolder.textViewTituloAgenda.setText(agenda.getNombre());
        viewHolder.textViewFechaAgenda.setText(agenda.getFecha_agendado());
        viewHolder.textViewDescripcion.setText(agenda.getDescripcion());
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(400).delay(50).playOn(layout.findViewById(R.id.lytdelete));
            }
        });

        viewHolder.lytDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                arrayListAgenda.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, arrayListAgenda.size());
                mItemManger.closeAllItems();
                AgendaFragment agendaFragment = new AgendaFragment();
                agendaFragment.EliminarTemasAgendados(agenda.getId(), RolId);
                Toast.makeText(v.getContext(),   viewHolder.textViewTituloAgenda.getText().toString() + " ELIMINADO! "+String.valueOf(agenda.getId()), Toast.LENGTH_LONG).show();
            }
        });

       Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.top_from_down : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);
        mItemManger.bindView(viewHolder.itemView, i);
        lastPosition = i;
    }

    @Override
    public int getItemCount() {
        return arrayListAgenda.size();
    }
}
