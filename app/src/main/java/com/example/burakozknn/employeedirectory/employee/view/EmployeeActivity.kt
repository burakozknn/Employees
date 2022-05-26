package com.example.burakozknn.employeedirectory.employee.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burakozknn.employeedirectory.R
import com.example.burakozknn.employeedirectory.databinding.ActivityEmployeeBinding
import com.example.burakozknn.employeedirectory.employee.model.EmployeeAdapter
import com.example.burakozknn.employeedirectory.employee.model.EmployeeModel
import com.example.burakozknn.employeedirectory.employee.viewmodel.EmployeeViewModel

class EmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(getString(R.string.toolbar_title))

        initialize()
    }

    private fun initialize() {
        //Setting click listener for buttons
        setButtonClickListener()

        //Initializing the ViewModel
        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        viewModel.liveDataList.observe(this, {
            it?.let {
                //Dismissing the progress bar
                binding.progressBar.visibility = View.GONE
                //Setting the adapter with data
                setAdapter(it)
                //Showing the toast message whether the employee list returns empty or not after successful response
                when(it.isEmpty()){
                    true -> showToastMessage(getString(R.string.empty_list))
                    false -> showToastMessage(getString(R.string.response_successful))
                }
            } ?: kotlin.run {
                //Dismissing the progress bar
                binding.progressBar.visibility = View.GONE
                //Showing the toast message
                showToastMessage(getString(R.string.failure))
            }
        })
    }

    fun showToastMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setAdapter(list: List<EmployeeModel>){
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@EmployeeActivity)
            adapter = EmployeeAdapter(this@EmployeeActivity, list).apply {
                notifyDataSetChanged()
            }
            visibility = View.VISIBLE
        }
    }

    private fun setButtonClickListener(){
        binding.apply {

            getEmployeesBtn.setOnClickListener {
                clearData(binding.recyclerview)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.callToGetEmployees()
            }

            getMalformedEmployeesBtn.setOnClickListener {
                clearData(binding.recyclerview)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.callToGetMalformedEmployees()
            }
            getEmptyEmployeeListBtn.setOnClickListener {
                clearData(binding.recyclerview)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.callToGetEmptyEmployeeList()
            }

            clearDataBtn.setOnClickListener {
                clearData(binding.recyclerview)
            }
        }
    }

    private fun clearData(recyclerview: RecyclerView){
        recyclerview.apply {
            adapter = null
            visibility = View.GONE
        }
    }
}