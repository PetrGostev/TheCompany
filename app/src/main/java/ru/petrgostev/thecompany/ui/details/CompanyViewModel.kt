package ru.petrgostev.thecompany.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.petrgostev.thecompany.data.Company
import ru.petrgostev.thecompany.repository.ICompanyRepository

class CompanyViewModel(private val companyRepository: ICompanyRepository) : ViewModel(){

    private val _mutableCompany = MutableLiveData<Company>()
    val company: LiveData<Company> get() = _mutableCompany

    private val _mutableIsError = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _mutableIsError

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _mutableIsError.postValue(true)
    }

    fun getCompany(id: String) {
        viewModelScope.launch(exceptionHandler) {
            val co : Company = companyRepository.getCompany(id)
            _mutableCompany.postValue(co)
        }
    }
}