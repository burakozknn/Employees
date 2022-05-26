package com.example.burakozknn.employeedirectory.employee.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EmployeeModel {

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null;

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null;

    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String? = null;

    @SerializedName("email_address")
    @Expose
    var email_address: String? = null;

    @SerializedName("biography")
    @Expose
    var biography: String? = null;

    @SerializedName("photo_url_small")
    @Expose
    var photoUrlSmall: String? = null;

    @SerializedName("photo_url_large")
    @Expose
    var photoUrlLarge: String? = null;

    @SerializedName("team")
    @Expose
    var team: String? = null;

    @SerializedName("employee_type")
    @Expose
    var employeeType: String? = null;

}
