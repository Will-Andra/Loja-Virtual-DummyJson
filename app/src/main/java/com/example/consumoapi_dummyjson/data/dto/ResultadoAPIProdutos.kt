package com.example.consumoapi_dummyjson.data.dto

data class ResultadoAPIProdutos(
    val limit: Int,
    val products: List<ProductDTO>,
    val skip: Int,
    val total: Int
)