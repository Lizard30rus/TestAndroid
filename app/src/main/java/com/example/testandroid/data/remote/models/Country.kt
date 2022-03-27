package com.example.testandroid.data.remote.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @PrimaryKey @SerializedName("Country") val countryName : String,
    @SerializedName("Slug") val slug : String,
    @SerializedName("ISO2") val ISO2 : String
)