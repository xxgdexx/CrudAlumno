package com.cibertec.simulacro_pc02.api;

import com.cibertec.simulacro_pc02.entity.Revista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceRevistaApi {

    @POST("revista")
    public abstract Call<Revista> insertaRevistar(@Body Revista obj);


}
