package com.example.burakozknn.employeedirectory.employee.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.burakozknn.employeedirectory.R
import com.example.burakozknn.employeedirectory.databinding.ItemEmployeeBinding
import com.example.burakozknn.employeedirectory.employee.view.EmployeeActivity

class EmployeeAdapter(private val activity: EmployeeActivity, private var employee: List<EmployeeModel>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        with(holder){
            bindView(employee[position])
        }
    }

    override fun getItemCount(): Int {
        return employee.size
    }

    inner class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding : ItemEmployeeBinding = ItemEmployeeBinding.bind(view)

        fun bindView(employee: EmployeeModel) {
            binding.apply {
                uuid.text = employee.uuid
                fullName.text = employee.fullName
                phoneNumber.text = employee.phoneNumber
                emailAddress.text = employee.email_address
                biography.text = employee.biography
                team.text = employee.team
                employeeType.text = employee.employeeType

                //Parsing the image url into imageview via Glide
                Glide
                    .with(activity)
                    .load(employee.photoUrlSmall)
                    .centerCrop()
                    .into(photoImg);
            }
        }
    }
}