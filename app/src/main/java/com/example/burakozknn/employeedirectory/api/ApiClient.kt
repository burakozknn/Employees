package com.example.burakozknn.employeedirectory.api

import com.example.burakozknn.employeedirectory.employee.model.Employees
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("employees.json")
    fun getEmployees() : Call<Employees>

    @GET("employees_malformed.json")
    fun getMalformedEmployees() : Call<Employees>

    @GET("employees_empty.json")
    fun getEmptyEmployeeList() : Call<Employees>

}