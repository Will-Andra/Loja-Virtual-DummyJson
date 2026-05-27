package com.example.consumoapi_dummyjson.data.dto

import com.example.consumoapi_dummyjson.domain.model.Produtos

data class ProductDTO(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val dimensions: Dimensions,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val meta: Meta,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val reviews: List<Review>,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
)

fun ProductDTO.toProdutos(): Produtos {
    return Produtos(
        id = id,
        titulo = title,
        descricao = description,
        preco = price,
        imagem = thumbnail
    )
}
