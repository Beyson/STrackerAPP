package com.example.ssis_tracker.repository.crearnoticia;

import com.example.ssis_tracker.api.noticia.ApiAdapterNoticia;
import com.example.ssis_tracker.api.noticia.ApiServiceNoticia;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.presenter.crearnoticia.CrearNoticiaFragmentPresenter;
import java.io.File;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearNoticiaFragmentRepositotyImpl implements CrearNoticiaFragmentRepositoty {

    private CrearNoticiaFragmentPresenter crearNoticiaFragmentPresenter;
    private ApiServiceNoticia apiServiceNoticia;

    public CrearNoticiaFragmentRepositotyImpl(CrearNoticiaFragmentPresenter crearNoticiaFragmentPresenter){
        this.crearNoticiaFragmentPresenter = crearNoticiaFragmentPresenter;
        this.apiServiceNoticia = new ApiAdapterNoticia().getClientService();
    }

    @Override
    public void CrearNoticia(String titulo, String noticia, ArrayList<String[]> Imagenes) {
       /* Call<ApiResponse> call = this.apiServiceNoticia.EnviarNoticia();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    crearNoticiaFragmentPresenter.NoticiaCreada(true, "");
                }else{
                    crearNoticiaFragmentPresenter.NoticiaCreada(false, "");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                crearNoticiaFragmentPresenter.NoticiaCreada(true, t.getMessage());
            }
        });*/
        File file = new File(Imagenes.get(0)[0]);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload",file.getName(),fileReqBody);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"),"image-type");
        Call<ApiResponse> call = this.apiServiceNoticia.CargarImagenesNoticia(part  , description);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
