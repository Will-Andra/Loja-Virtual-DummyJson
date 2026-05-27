package com.example.consumoapi_dummyjson.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumoapi_dummyjson.databinding.ItemviewBinding
import com.example.consumoapi_dummyjson.domain.model.Produtos
import com.example.consumoapi_dummyjson.util.MoedaFormatacao
import java.util.Locale

class ProdutoAdapter(
    private val onClick: (Produtos) -> Unit
) : ListAdapter<Produtos, ProdutoAdapter.ProdutoViewHolder>(DiffCallback) {


    private val formatterPrice = MoedaFormatacao()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = getItem(position)
        holder.bind(produto)
    }

    inner class ProdutoViewHolder(
        private val binding: ItemviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(produto: Produtos) {
            binding.txtIDProduto.text = produto.id.toString()
            binding.txtTituloProduto.text = produto.titulo
            binding.txtPrecoProduto.text = formatterPrice.formatToBRL(produto.preco)
           // binding.txtPrecoProduto.text = String.format(Locale.getDefault(), "R$ %.2f", produto.preco)

            Glide.with(binding.imgProduto.context)
                .load(produto.imagem)
                .into(binding.imgProduto)

            binding.root.setOnClickListener {
                onClick(produto)
            }
        }
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Produtos>() {
        override fun areItemsTheSame(oldItem: Produtos, newItem: Produtos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Produtos, newItem: Produtos): Boolean {
            return oldItem == newItem
        }
    }
}