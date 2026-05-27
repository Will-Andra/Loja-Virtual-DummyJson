package com.example.consumoapi_dummyjson.data.remote

import com.example.consumoapi_dummyjson.data.dto.ResultadoAPIProdutos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyAPI {

    //dummyjson.com/products
    @GET("products")
    suspend fun recuperaProdutos(): Response<ResultadoAPIProdutos>

    @GET("products")
    suspend fun recuperaProdutosComSkip(
        @Query("skip") skipLista: Int,
        @Query("limit") limitLista: Int
    ): Response<ResultadoAPIProdutos>
}