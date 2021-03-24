package ru.petrgostev.thecompany.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("img")
    val img: String,

    @SerialName("description")
    val description: String? = null,

    @SerialName("lat")
    val lat: Double? = 0.0,

    @SerialName("lon")
    val lon: Double? = 0.0,

    @SerialName("www")
    val www: String? = null,

    @SerialName("phone")
    val phone: String? = null
) {
    val image = BASE_URL_IMAGE + img

    companion object {
        private const val BASE_URL_IMAGE = "https://lifehack.studio/test_task/"
    }
}