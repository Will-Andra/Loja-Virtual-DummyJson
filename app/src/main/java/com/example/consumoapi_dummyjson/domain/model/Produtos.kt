package com.example.consumoapi_dummyjson.domain.model

import android.os.Parcelable
import java.io.Serializable

data class Produtos (
    var id: Int,
    var titulo: String,
    var descricao: String,
    var preco: Double,
    var imagem: String
): Serializable