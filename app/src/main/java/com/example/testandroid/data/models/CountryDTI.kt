package com.example.testandroid.data.models

import com.google.gson.annotations.SerializedName

/**
 * модель, заполняемая данными из back, для списка стран
 */
data class CountryDTI(
    @SerializedName("Country") val countryName : String,
    @SerializedName("Slug") val slug : String
)