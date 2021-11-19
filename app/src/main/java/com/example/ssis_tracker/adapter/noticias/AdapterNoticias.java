package com.example.ssis_tracker.adapter.noticias;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.Noticias;
import java.util.ArrayList;

public class AdapterNoticias extends RecyclerView.Adapter<AdapterNoticias.HolderNotificaciones> {

    private ArrayList<Noticias> arrayListNotificaciones;
    private Context context;

    public AdapterNoticias(Context context )
    {
        this.context = context;
        ArrayList<Actividad.documentos_adjuntos> imagen = new ArrayList<>();

        this.arrayListNotificaciones = new ArrayList<>();
        Noticias notificaciones = new Noticias();
        notificaciones.setTitulo_notificacion("Lorem Ipsum");
        notificaciones.setMensaje_notificacion("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium");
        notificaciones.setUsuaio_notificacion("Usuario 1");
        notificaciones.setFecha_notificacion("1-junio-2019");
        notificaciones.setImagenes( imagen );

        Noticias notificaciones2 = new Noticias();
        notificaciones2.setTitulo_notificacion("Lorem Ipsum");
        notificaciones2.setMensaje_notificacion("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium");
        notificaciones2.setUsuaio_notificacion("Usuario 2");
        notificaciones2.setFecha_notificacion("1-junio-2019");


        ArrayList<Actividad.documentos_adjuntos> imagen2 = new ArrayList<>();
       // imagen2.add();
     //   imagen2.add("https://source.unsplash.com/user/erondu/daily");
      //  imagen2.add("https://source.unsplash.com/user/erondu/daily");

        notificaciones2.setImagenes( imagen2 );
        this.arrayListNotificaciones.add(notificaciones);
        this.arrayListNotificaciones.add(notificaciones2);
  }

    @NonNull
    @Override
    public HolderNotificaciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_noticias, viewGroup , false);
        return new HolderNotificaciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNotificaciones holderNotificaciones, int i) {

        holderNotificaciones.TextViewHeaderNotificaciones.setText(this.arrayListNotificaciones.get(i).getTitulo_notificacion());
        holderNotificaciones.TextViewMensajeNotificacion.setText(this.arrayListNotificaciones.get(i).getMensaje_notificacion());
        holderNotificaciones.TextViewUsuarioNotificacion.setText(this.arrayListNotificaciones.get(i).getUsuaio_notificacion());
        holderNotificaciones.TextViewFechaNotificacion.setText(this.arrayListNotificaciones.get(i).getFecha_notificacion());
        holderNotificaciones.TextViewCantidadImagen.setText(String.valueOf(this.arrayListNotificaciones.get(i).getImagenes().size())+" imagenes en la noticia");

        if(this.arrayListNotificaciones.get(i).getImagenes().size()==0){
            holderNotificaciones.RelativeLayoutImagenesNotificacion.setVisibility(View.GONE);
        }else{
            holderNotificaciones.recyclerViewImagenNotificacion.setVisibility(View.VISIBLE);
            LinearLayoutManager lym = new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL , false);
            AdapterImagenesNoticias adapterImagenesNotificacion = new AdapterImagenesNoticias(this.context);

            for(int x = 0 ; x < this.arrayListNotificaciones.get(i).getImagenes().size(); x++){
                adapterImagenesNotificacion.AgregarImagen(this.arrayListNotificaciones.get(i).getImagenes().get(x));
            }

            holderNotificaciones.recyclerViewImagenNotificacion.setLayoutManager(lym);
            holderNotificaciones.recyclerViewImagenNotificacion.setAdapter(adapterImagenesNotificacion);
        }
    }



    @Override
    public int getItemCount() {
        return this.arrayListNotificaciones.size();
    }


    public class HolderNotificaciones extends RecyclerView.ViewHolder {

        private TextView     TextViewHeaderNotificaciones;
        private TextView     TextViewMensajeNotificacion;
        private RecyclerView recyclerViewImagenNotificacion;
        private RelativeLayout RelativeLayoutImagenesNotificacion;
        private TextView     TextViewUsuarioNotificacion;
        private TextView     TextViewFechaNotificacion;
        private TextView     TextViewCantidadImagen;

        public HolderNotificaciones(@NonNull View itemView) {
            super(itemView);

            this.TextViewHeaderNotificaciones = itemView.findViewById(R.id.TextViewHeaderNotificaciones);
            this.TextViewMensajeNotificacion  = itemView.findViewById(R.id.TextViewMensajeNotificacion);
            this.recyclerViewImagenNotificacion = itemView.findViewById(R.id.recyclerViewImagenNotificacion);
            this.RelativeLayoutImagenesNotificacion = itemView.findViewById(R.id.RelativeLayoutImagenesNotificacion);
            this.TextViewUsuarioNotificacion  = itemView.findViewById(R.id.TextViewUsuarioNotificacion);
            this.TextViewFechaNotificacion    = itemView.findViewById(R.id.TextViewFechaNotificacion);
            this.TextViewCantidadImagen       = itemView.findViewById(R.id.TextViewCantidadImagen);
        }
    }
}
