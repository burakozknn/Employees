package com.example.burakozknn.employeedirectory.employee.viewmodel

import androidx.lifecycle.ViewModel
import com.example.burakozknn.employeedirectory.api.ApiService
import com.example.burakozknn.employeedirectory.employee.model.EmployeeRepository


class EmployeeViewModel(private val repository: EmployeeRepository = EmployeeRepository(ApiService)) : ViewModel() {

    var liveDataList = repository.repositoryData

    //fetching LiveData from repository to ViewModel for the employee list
    fun callToGetEmployees() {
        repository.getEmployees()
    }

    //fetching LiveData from repository to ViewModel for the malformed employee list
    fun callToGetMalformedEmployees() {
        repository.getMalformedEmployees()
    }

    //fetching LiveData from repository to ViewModel for the empty employee list
    fun callToGetEmptyEmployeeList() {
        repository.getEmptyEmployeeList()
    }
}