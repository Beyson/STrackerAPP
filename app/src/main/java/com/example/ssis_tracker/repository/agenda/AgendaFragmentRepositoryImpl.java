package com.example.ssis_tracker.repository.agenda;

import com.example.ssis_tracker.api.agenda.ApiAdapterAgenda;
import com.example.ssis_tracker.api.agenda.ApiServiceAgenda;
import com.example.ssis_tracker.model.Agenda;
import com.example.ssis_tracker.model.ApiResponse;
import com.example.ssis_tracker.presenter.agenda.AgendaFragmentPresenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaFragmentRepositoryImpl implements AgendaFragmentRepository {

    private AgendaFragmentPresenter agendaFragmentPresenter;
    private ApiServiceAgenda servicesAgenda;

    public AgendaFragmentRepositoryImpl(AgendaFragmentPresenter agendaFragmentPresenter){
        this.agendaFragmentPresenter = agendaFragmentPresenter;
        this.servicesAgenda = new ApiAdapterAgenda().getClientService();
    }

    @Override
    public void GetTemasAgendados() {
        Call<ArrayList<Agenda>> ListarAgenda = this.servicesAgenda.ListarTemaAgenda();
        ListarAgenda.enqueue(new Callback<ArrayList<Agenda>>() {
            @Override
            public void onResponse(Call<ArrayList<Agenda>> call, Response<ArrayList<Agenda>> response) {
                agendaFragmentPresenter.ListarTemasAgendados(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Agenda>> call, Throwable t) {
                agendaFragmentPresenter.ErrorListarTemas(t.getMessage());
            }
        });
    }

    @Override
    public void EliminarTemasAgendados(int IdTema , int idRol) {
        Call<ApiResponse> EliminarAgenda = this.servicesAgenda.EliminarTemaAgenda(IdTema , idRol);
        EliminarAgenda.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) { }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) { }
        });
    }

}
