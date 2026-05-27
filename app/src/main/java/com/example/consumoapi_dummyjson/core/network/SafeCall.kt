package com.example.consumoapi_dummyjson.core.network

import com.example.consumoapi_dummyjson.domain.error.AppError
import com.example.consumoapi_dummyjson.presentation.state.ResultadoState
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException


inline fun <T> safeCallAPI(
    bloco: () -> Response<T>
): ResultadoState<T> {
    return try {
        val resposta = bloco()

        if (resposta.isSuccessful) {
            val corpo = resposta.body()
            if (corpo != null) {
                ResultadoState.Sucesso(corpo)
            } else {
                ResultadoState.Erro(AppError.UnknownError)
            }
        } else {

            ResultadoState.Erro(
                when (resposta.code()) {
                    401 -> AppError.UnauthorizedError
                    403 -> AppError.ForbiddenError
                    404 -> AppError.NotFoundError
                    in 500..599 -> AppError.ServerError
                    else -> AppError.UnknownError
                }
            )
        }
    } catch (e: SocketTimeoutException) {
        ResultadoState.Erro(AppError.TimeoutError)

    } catch (e: IOException) {
        ResultadoState.Erro(AppError.NetworkError)

    } catch (e: Exception) {
        ResultadoState.Erro(AppError.UnknownError)
    }
}
