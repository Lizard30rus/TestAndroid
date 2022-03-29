package com.example.testandroid.data.models

import com.google.gson.annotations.SerializedName
import java.util.*


/**
 * модель, заполняемая данными из back, детальная информация по стране
 */
data class CountryDetailDTI(
    @SerializedName ("Country") val countryName : String,
    @SerializedName ("Confirmed") val counfirmed : String,
    @SerializedName ("Deaths") val deaths : String,
    @SerializedName ("Recovered") val recovered : String,
    @SerializedName ("Active") val active : String,
    //@SerializedName ("Date") val date : Date
)
