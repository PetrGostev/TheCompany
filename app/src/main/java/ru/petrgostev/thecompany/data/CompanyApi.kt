package ru.petrgostev.thecompany.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyApi {

    @GET("test.php")
    suspend fun getCompanies(): List<Company>

    @GET("test.php")
    suspend fun getCompany(@Query("id") id : String): List<Company>
}