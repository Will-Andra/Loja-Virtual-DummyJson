package com.example.consumoapi_dummyjson.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.domain.usecase.GetProdutosUseCase
import com.example.consumoapi_dummyjson.presentation.state.ResultadoState
import kotlinx.coroutines.launch

class ProdutoViewModel(
    private val produtosUseCase: GetProdutosUseCase
) : ViewModel() {

    private var currentSkip = 0
    private val limit = 30
    private var isQuerying = false // Evita cliques/scrolls duplicados

    private val _produtos = MutableLiveData<List<Produtos>>()
    val produtos: LiveData<List<Produtos>> = _produtos

    private val _erro = MutableLiveData<String?>()
    val erro: LiveData<String?> = _erro

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val listaAcumulada = mutableListOf<Produtos>()

    init {
        recuperarProdutos() // Carrega a primeira página ao abrir
    }

    fun recuperarProdutos() {
        if (isQuerying) return
        isQuerying = true

        viewModelScope.launch {
            // Só ativa o loading se for a primeira página para não piscar a tela no scroll
            if (currentSkip == 0) _loading.value = true
            _erro.value = null

            when (val result = produtosUseCase(limit, currentSkip)) {
                is ResultadoState.Sucesso -> {
                    val novosProdutos = result.data

                    if (novosProdutos.isNotEmpty()) {

                        listaAcumulada.addAll(novosProdutos)

                        _produtos.value = listaAcumulada.toList()

                        currentSkip += limit
                    }
                }

                is ResultadoState.Erro -> {
                    _erro.value = result.error.toMessage()
                }
            }

            _loading.value = false
            isQuerying = false
        }
    }
}