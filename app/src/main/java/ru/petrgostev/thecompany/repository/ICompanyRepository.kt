package ru.petrgostev.thecompany.repository

import ru.petrgostev.thecompany.data.Company

interface ICompanyRepository {
    suspend fun getCompanies(): List<Company>
    suspend fun getCompany(id: String): Company

}