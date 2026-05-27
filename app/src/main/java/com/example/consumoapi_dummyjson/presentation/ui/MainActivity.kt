package com.example.consumoapi_dummyjson.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.consumoapi_dummyjson.data.remote.RetrofitService
import com.example.consumoapi_dummyjson.data.repository.ProdutoRepositoryImpl
import com.example.consumoapi_dummyjson.databinding.ActivityMainBinding
import com.example.consumoapi_dummyjson.domain.usecase.GetProdutosUseCase
import com.example.consumoapi_dummyjson.presentation.viewModel.ProdutoViewModel
import com.example.consumoapi_dummyjson.presentation.viewModel.ProdutoViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: ProdutoViewModel

    private val produtoAdapter by lazy {
        ProdutoAdapter { produto ->
            val intent = Intent(this, DetalhesProduto::class.java).apply {
                putExtra("PRODUTO_SELECIONADO", produto)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Loja Virtual API"

        inicializarViewModel()
        configurarRecyclerView()
        observarEstados()
    }

    private fun inicializarViewModel() {
        val api = RetrofitService.dummyAPI
        val repository = ProdutoRepositoryImpl(api)
        val useCase = GetProdutosUseCase(repository)
        val factory = ProdutoViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory)[ProdutoViewModel::class.java]
    }

    private fun configurarRecyclerView() {
        binding.rvProdutos.adapter = produtoAdapter

        binding.rvProdutos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.recuperarProdutos()
                }
            }
        })
    }

    private fun observarEstados() {
        viewModel.produtos.observe(this) { lista ->
            produtoAdapter.submitList(lista)
        }

        viewModel.loading.observe(this) { estaCarregando ->
            if (estaCarregando) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.erro.observe(this) { mensagemErro ->
            mensagemErro?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}
