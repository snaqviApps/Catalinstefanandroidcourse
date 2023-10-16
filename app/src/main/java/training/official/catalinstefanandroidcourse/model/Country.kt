package training.official.catalinstefanandroidcourse.model

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("name") val countryName: String?,
    @SerializedName("capital") val capital: String?,
    @SerializedName("flagPNG") val flag: String?,
)