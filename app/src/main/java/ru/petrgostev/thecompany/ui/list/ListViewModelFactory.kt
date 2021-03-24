package ru.petrgostev.thecompany.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.petrgostev.thecompany.repository.ICompanyRepository

class ListViewModelFactory(private val companyRepository: ICompanyRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ListViewModel::class.java -> ListViewModel(companyRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}