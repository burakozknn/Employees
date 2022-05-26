package com.example.burakozknn.employeedirectory.employee.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Employees {

    @SerializedName("employees")
    @Expose
    var employees: List<EmployeeModel>? = arrayListOf()

}