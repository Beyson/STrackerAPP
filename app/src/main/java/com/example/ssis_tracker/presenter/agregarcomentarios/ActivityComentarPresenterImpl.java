package com.example.ssis_tracker.presenter.agregarcomentarios;

import android.view.View;

import com.example.ssis_tracker.interactor.agregarcomentarios.ActivityComentarInteractor;
import com.example.ssis_tracker.interactor.agregarcomentarios.ActivityComentarInteractorImpl;
import com.example.ssis_tracker.view.agregarcomentarios.ActivityComentarView;

public class ActivityComentarPresenterImpl implements ActivityComentarPresenter {

    private ActivityComentarView activityComentarView;
    private ActivityComentarInteractor activityComentarInteractor;

    public ActivityComentarPresenterImpl(ActivityComentarView activityComentarView , View view){
        this.activityComentarView = activityComentarView;
        this.activityComentarInteractor = new ActivityComentarInteractorImpl(this , view);
    }

    @Override
    public void AgregarComentario(String Usuario , String Comentario , int Actividad) {
        this.activityComentarInteractor.AgregarComentario(Usuario , Comentario , Actividad);
    }

    @Override
    public void ComentarioAgregado(int Repuesta) {
        this.activityComentarView.ComentarioAgregado(Repuesta);
    }
}
