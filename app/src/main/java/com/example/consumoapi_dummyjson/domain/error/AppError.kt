package com.example.consumoapi_dummyjson.domain.error

sealed class AppError {
    object NetworkError: AppError()
    object NotFoundError: AppError()
    object ServerError: AppError()
    object UnknownError: AppError()
    object UnauthorizedError: AppError()
    object ForbiddenError: AppError()
    object TimeoutError: AppError()
    data class CustomError( val message: String): AppError()

    fun toMessage(): String {
        return when (this) {
            NetworkError -> "Sem conexão com a internet"
            NotFoundError -> "Recurso não encontrado"
            ServerError -> "Erro interno no servidor. Tente novamente mais tarde"
            UnknownError -> "Erro desconhecido"
            UnauthorizedError -> "Acesso não autorizado"
            ForbiddenError -> "Você não tem permissão para acessar este recurso"
            TimeoutError -> "Tempo de conexão excedido. Verifique sua internet"
            is CustomError -> message
        }
    }

}