package com.example.coonectingapiwithsqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coonectingapiwithsqlite.databinding.ProductItemBinding

class RecyclerAdapter(private val productList: List<Product> ,
                      private val onItemClick: (Product) -> Unit ) :
        RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = productList[position]
            holder.bind(product)
        }

        override fun getItemCount(): Int = productList.size

        inner class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(product: Product) {
                binding.productTitle.text = product.title
                binding.productBrand.text = product.brand
                binding.productRating.text = product.rating.toString()
                binding.productPrice.text = product.price.toString()

                Glide.with(binding.root.context)
                    .load(product.images.first()) // Assuming images is a list of URLs or resource IDs
                    .into(binding.productImage)
                // Bind other properties as needed

                binding.root.setOnClickListener {
                    onItemClick(product)
                }
            }
        }
    }
