package ru.petrgostev.thecompany.repository

import ru.petrgostev.thecompany.data.Company
import ru.petrgostev.thecompany.data.NetworkModule

class CompanyRepository(private val networkModule: NetworkModule) : ICompanyRepository {

    override suspend fun getCompanies(): List<Company> = networkModule.companyApi.getCompanies()

    override suspend fun getCompany(id: String): Company =
        networkModule.companyApi.getCompany(id)[0]

    companion object {
        private var instance: CompanyRepository? = null

        fun getInstance(
            networkModule: NetworkModule
        ): CompanyRepository {
            return instance ?: synchronized(this) {
                instance ?: CompanyRepository(
                    networkModule
                ).also {
                    instance = it
                }
            }
        }
    }
}