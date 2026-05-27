package com.example.consumoapi_dummyjson.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.consumoapi_dummyjson.domain.usecase.GetProdutosUseCase

class ProdutoViewModelFactory(
    private val produtosUseCase: GetProdutosUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProdutoViewModel::class.java)) {
            return ProdutoViewModel(produtosUseCase) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}