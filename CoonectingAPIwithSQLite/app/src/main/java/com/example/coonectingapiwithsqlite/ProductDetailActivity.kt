package com.example.coonectingapiwithsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coonectingapiwithsqlite.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>("product")

        product?.let {
            binding.detailedProductName.text = it.title
            binding.detailedProductBrand.text = it.brand
            binding.detailedProductCategory.text = it.category
            binding.detailedProductDescription.text = it.description
            binding.detailedProductRating.text = it.rating.toString()
            binding.detailedProductPrice.text = it.price.toString()
            Glide.with(this)
                .load(it.images.first()) // Assuming images is a list of URLs or resource IDs
                .into(binding.detailedProductImage)
        }

        binding.backBtn.setOnClickListener{
            startActivity(Intent(this , MainActivity::class.java))
        }

        binding.wishlistBtn.setOnClickListener{
            startActivity(Intent(this , WishlistActivity::class.java))
        }

        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this , CartActivity::class.java))
        }

        binding.addToCart.setOnClickListener{
            Toast.makeText(this, "Item Added to Cart", Toast.LENGTH_SHORT).show()
        }
    }
}
