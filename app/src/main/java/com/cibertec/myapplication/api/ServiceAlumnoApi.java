package com.cibertec.myapplication.api;

import android.widget.EditText;

import com.cibertec.myapplication.entity.Alumno;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceAlumnoApi {

    @GET("alumno")
    public abstract Call<List<Alumno>> listaAlumno();

    @GET("alumno/{id}")
    public abstract Call<Optional<Alumno>> buscaAlumno(String id);

    @POST("alumno")
    public abstract Call<Alumno> insertaAlumno(@Body Alumno obj);

    @PUT("alumno")
    public abstract Call<Alumno> actualizaAlumno(@Body Alumno obj);

    @DELETE("alumno/{id}")
    public abstract Call<Alumno> eliminaAlumno(@Path("id")String id);



}
