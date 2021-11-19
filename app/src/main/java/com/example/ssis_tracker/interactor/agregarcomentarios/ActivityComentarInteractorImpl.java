package com.example.ssis_tracker.interactor.agregarcomentarios;

import android.view.View;
import com.example.ssis_tracker.presenter.agregarcomentarios.ActivityComentarPresenter;
import com.example.ssis_tracker.repository.agregarcomentarios.ActivityComentarRepository;
import com.example.ssis_tracker.repository.agregarcomentarios.ActivityComentarRepositoryImpl;
import com.google.gson.JsonObject;

public class ActivityComentarInteractorImpl implements ActivityComentarInteractor {

    private ActivityComentarRepository activityComentarRepository;

    public ActivityComentarInteractorImpl(ActivityComentarPresenter activityComentarPresenter , View view){
        this.activityComentarRepository = new ActivityComentarRepositoryImpl(activityComentarPresenter , view);
    }

    @Override
    public void AgregarComentario(String Usuario , String Comentario , int Actividad ) {

        JsonObject JsonObjectComentario = new JsonObject();
        JsonObjectComentario.addProperty("usuario"    , Usuario);
        JsonObjectComentario.addProperty("comentario" , Comentario);
        JsonObjectComentario.addProperty("actividad"  , String.valueOf(Actividad));

        this.activityComentarRepository.AgregarComentario(JsonObjectComentario);
    }
}
