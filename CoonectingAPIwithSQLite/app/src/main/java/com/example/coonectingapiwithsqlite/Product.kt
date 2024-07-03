package com.example.coonectingapiwithsqlite

import android.os.Parcel
import android.os.Parcelable

//MAKING DATA CLASS PARCEBLE WE ARE MAKING THE DATA CLASS PARCEBLE

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Double,
    val rating: Double,
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createStringArrayList() ?: listOf(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeStringList(images)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeString(title)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
