package com.cibertec.myapplication.api;


import com.cibertec.myapplication.entity.Revista;

import java.util.List;

import retrofit2.Call;


import retrofit2.http.Body;

import retrofit2.http.DELETE;

import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.PUT;

import retrofit2.http.Path;

public interface ServiceRevistaApi {

    @POST("revista")
    public abstract Call<Revista> insertaRevistar(@Body Revista obj);

    @PUT("revista")
    public abstract Call<Revista> actualizaRevista(@Body Revista obj);

    @GET("revista")
    public abstract List <Call<Revista>> listaRevista();

    @GET("revista/{id}")
    public abstract Call<Revista> listaRevistaPorId(@Path(value = "id") String id);

    @DELETE("revista/{id}")
    public abstract Call<Revista>eliminaRevistarPorId(@Path(value="id") String id);

}
