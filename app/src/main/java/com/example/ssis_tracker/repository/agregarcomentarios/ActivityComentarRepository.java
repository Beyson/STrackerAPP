package com.example.ssis_tracker.repository.agregarcomentarios;

import com.google.gson.JsonObject;

public interface ActivityComentarRepository {
    void AgregarComentario(JsonObject jsonComentario);
}
