package ru.petrgostev.thecompany.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.petrgostev.thecompany.repository.ICompanyRepository

class CompanyViewModelFactory(private val companyRepository: ICompanyRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        CompanyViewModel::class.java -> CompanyViewModel(companyRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}