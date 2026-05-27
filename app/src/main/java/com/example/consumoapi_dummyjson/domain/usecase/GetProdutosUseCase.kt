package com.example.consumoapi_dummyjson.domain.usecase

import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.domain.repository.ProdutosRepository
import com.example.consumoapi_dummyjson.presentation.state.ResultadoState

class GetProdutosUseCase(
    private val repository: ProdutosRepository
) {

    suspend operator fun invoke(
        skipLista: Int,
        limitLista: Int
    ): ResultadoState<List<Produtos>> {
        return repository.recuperaProdutosComSkip(skipLista, limitLista)
    }
}
