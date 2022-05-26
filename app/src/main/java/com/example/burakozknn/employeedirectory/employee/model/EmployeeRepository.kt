package com.example.burakozknn.employeedirectory.employee.model

import androidx.lifecycle.MutableLiveData
import com.example.burakozknn.employeedirectory.api.ApiClient
import com.example.burakozknn.employeedirectory.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class EmployeeRepository(service: ApiService) {
    //Declaring our api client here so that we can pass it over to ViewModel when we call retrofit in there.
    private val getService = service.buildService(ApiClient::class.java)

    val repositoryData: MutableLiveData<List<EmployeeModel>> = MutableLiveData()

    //Calling api to fetch the employee list
    fun getEmployees() {
        getService.getEmployees().enqueue(object : Callback<Employees> {

            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                when (response.code()) {
                    200 -> repositoryData.postValue(response.body()?.employees) //adding data from response into liveDataList when successful
                    else -> repositoryData.postValue(null) //setting liveDataList = null when failure
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                t.message?.let {
                    Timber.d(it) //Logging the error message
                }
                repositoryData.postValue(null) //setting liveDataList = null when failure
            }
        })
    }

    //Calling api to fetch the malforned employee list
    fun getMalformedEmployees() {
        getService.getMalformedEmployees().enqueue(object : Callback<Employees> {

            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                when (response.code()) {
                    200 -> repositoryData.postValue(response.body()?.employees) //adding data from response into liveDataList when successful
                    else -> repositoryData.postValue(null) //setting liveDataList = null when failure
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                t.message?.let {
                    Timber.d(it) //Logging the error message
                }
                repositoryData.postValue(null) //setting liveDataList = null when failure
            }
        })
    }

    //Calling api to fetch the empty employee list
    fun getEmptyEmployeeList() {
        getService.getEmptyEmployeeList().enqueue(object : Callback<Employees> {

            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                when (response.code()) {
                    200 -> repositoryData.postValue(response.body()?.employees) //adding data from response into liveDataList when successful
                    else -> repositoryData.postValue(null) //setting liveDataList = null when failure
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                t.message?.let {
                    Timber.d(it) //Logging the error message
                }
                repositoryData.postValue(null) //setting liveDataList = null when failure
            }
        })
    }
}