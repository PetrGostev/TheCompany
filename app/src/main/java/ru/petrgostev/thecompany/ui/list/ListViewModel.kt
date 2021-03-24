package ru.petrgostev.thecompany.ui.list

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.petrgostev.thecompany.data.Company
import ru.petrgostev.thecompany.repository.ICompanyRepository

class ListViewModel(private val companyRepository: ICompanyRepository) : ViewModel() {

    private val _mutableCompanyList = MutableLiveData<List<Company>>()
    val companyList: LiveData<List<Company>> get() = _mutableCompanyList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(ContentValues.TAG, "Coroutine exception, scope active:${viewModelScope.isActive}", throwable)
    }

    fun getCompanyList() {
        viewModelScope.launch(exceptionHandler) {
            _mutableCompanyList.postValue(companyRepository.getCompanies())
        }
    }
}