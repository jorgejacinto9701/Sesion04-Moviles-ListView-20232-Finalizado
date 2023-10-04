package com.negocio.semana07_recyclerviewconglide_202302.service;


import com.negocio.semana07_recyclerviewconglide_202302.entity.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceProducto {

    @GET("products")
    public abstract Call<List<Producto>> listaProducto();

}
