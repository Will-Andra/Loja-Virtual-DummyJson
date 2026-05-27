package com.example.consumoapi_dummyjson.data.repository

import com.example.consumoapi_dummyjson.core.network.safeCallAPI
import com.example.consumoapi_dummyjson.data.remote.RetrofitService
import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.domain.repository.ProdutosRepository
import com.example.consumoapi_dummyjson.presentation.state.ResultadoState

// ⚠️ AJUSTE O IMPORT ABAIXO PARA O PACOTE EXATO DO SEU MAPPER:
import com.example.consumoapi_dummyjson.data.dto.toProdutos
import com.example.consumoapi_dummyjson.data.dto.ResultadoAPIProdutos
import com.example.consumoapi_dummyjson.data.remote.DummyAPI

class ProdutoRepositoryImpl(
    private val dummyAPI: DummyAPI
) : ProdutosRepository {

    override suspend fun recuperaProdutosComSkip(
        skipLista: Int,
        limitLista: Int
    ): ResultadoState<List<Produtos>> {


        val resultadoAPI = safeCallAPI<ResultadoAPIProdutos> {
            dummyAPI.recuperaProdutosComSkip(skipLista, limitLista)
        }

        return when (resultadoAPI) {
            is ResultadoState.Sucesso -> {

                val listaDominio = resultadoAPI.data.products.map { productDTO ->
                    productDTO.toProdutos()
                }
                ResultadoState.Sucesso(listaDominio)
            }
            is ResultadoState.Erro -> {

                ResultadoState.Erro(resultadoAPI.error)
            }
        }
    }
}