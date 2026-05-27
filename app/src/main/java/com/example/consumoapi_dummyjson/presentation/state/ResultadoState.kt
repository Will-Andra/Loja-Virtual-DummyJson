package com.example.consumoapi_dummyjson.presentation.state

import com.example.consumoapi_dummyjson.domain.error.AppError

sealed class ResultadoState<out T> {

    data class Sucesso<T>(
        val data: T
    ) : ResultadoState<T>()

    data class Erro(
        val error: AppError
    ) : ResultadoState<Nothing>()

}