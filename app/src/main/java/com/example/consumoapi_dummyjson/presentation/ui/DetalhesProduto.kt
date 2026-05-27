package com.example.consumoapi_dummyjson.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.consumoapi_dummyjson.databinding.ActivityDetalhesProdutoBinding
import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.util.MoedaFormatacao

class DetalhesProduto : AppCompatActivity() {

    private val binding by lazy { ActivityDetalhesProdutoBinding.inflate(layoutInflater) }
    private val formatterPrice = MoedaFormatacao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Detalhes do Produto"

        // Recupera o objeto de forma segura tratando a depreciação do getSerializableExtra
        val produto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("PRODUTO_SELECIONADO", Produtos::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("PRODUTO_SELECIONADO") as? Produtos
        }

        // Preenche a tela se o produto não for nulo
        produto?.let {
            preencherCampos(it)
        }
    }

    private fun preencherCampos(produto: Produtos) {
        binding.txtTituloDetalhe.text = produto.titulo
        binding.txtDescricaoDetalhe.text = produto.descricao
        binding.txtPrecoDetalhe.text = formatterPrice.formatToBRL(produto.preco)

        Glide.with(this)
            .load(produto.imagem)
            .into(binding.imgDetalhe)
    }
}