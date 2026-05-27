package com.example.consumoapi_dummyjson.domain.repository

import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.presentation.state.ResultadoState

interface ProdutosRepository {

    suspend fun recuperaProdutosComSkip(
        skipLista: Int,
        limitLista: Int
    ): ResultadoState<List<Produtos>>


}