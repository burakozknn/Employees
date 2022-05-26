package com.example.burakozknn.employeedirectory.employee.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.burakozknn.employeedirectory.employee.model.EmployeeModel
import com.example.burakozknn.employeedirectory.employee.model.EmployeeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmployeeViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EmployeeViewModel

    @MockK(relaxUnitFun = true)
    private lateinit var repository: EmployeeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.repositoryData } returns MutableLiveData()
        viewModel = EmployeeViewModel(repository)
    }

    @Test
    fun `fetch employees and check response Code 200 returned`() {

        val list = EmployeeModel().apply {
            fullName = "Burak"
        }

        every { repository.getEmployees() } answers {
            repository.repositoryData.postValue(listOf(list))
        }

        viewModel.callToGetEmployees()
        assertEquals(viewModel.liveDataList.value, listOf(list))
        assertEquals(viewModel.liveDataList.value?.get(0)?.fullName, "Burak")
    }

    @Test
    fun `fetch malformed employees and check response Code 200 returned`() {

        val list = EmployeeModel().apply {
            fullName = "Burak"
            team = null
        }

        every { repository.getMalformedEmployees() } answers {
            repository.repositoryData.postValue(listOf(list))
        }

        viewModel.callToGetMalformedEmployees()
        assertEquals(viewModel.liveDataList.value, listOf(list))
        assertEquals(viewModel.liveDataList.value?.get(0)?.fullName, "Burak")
        assertEquals(viewModel.liveDataList.value?.get(0)?.team, null)
    }

    @Test
    fun `fetch employees and check response Code 200 amd empty list returned`() {

        val list = EmployeeModel().apply {
            fullName = null
        }

        every { repository.getEmptyEmployeeList() } answers {
            repository.repositoryData.postValue(listOf(list))
        }

        viewModel.callToGetEmptyEmployeeList()
        assertEquals(viewModel.liveDataList.value, listOf(list))
        assertEquals(viewModel.liveDataList.value?.get(0)?.fullName, null)
    }

    @Test
    fun `fetch employees and check response failure`() {
        every { repository.getEmployees() } answers {
            repository.repositoryData.postValue(null)
        }

        viewModel.callToGetEmployees()
        assertEquals(viewModel.liveDataList.value, null)
    }
}