package com.example.consumoapi_dummyjson.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
   //Classe criada apenas para estudo a API não demanda token

    override fun intercept(intercerpt: Interceptor.Chain?): Response? {
        val requisicao = intercerpt?.request()?.newBuilder()

        val requestAtual = intercerpt?.request()?.url()
        val requestNova = requestAtual?.newBuilder()
         requestNova?.addQueryParameter("token", "123456")
        //toke falso, parâmetro ustilizado apenas para estudo. API não solicita token

        requisicao?.url(requestNova?.build())
        return intercerpt?.proceed(requisicao?.build())
    }

}