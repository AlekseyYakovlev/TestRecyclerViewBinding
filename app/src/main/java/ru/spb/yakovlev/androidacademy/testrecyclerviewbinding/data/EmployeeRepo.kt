package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee

object EmployeeRepo : DataRepo {

    val employeeList: LiveData<List<Employee>>
    get() = mutableEmployeeList

    private val mutableEmployeeList = MutableLiveData<List<Employee>>()
    private val tempEmployeeList = mutableListOf<Employee>()

    init {
        for ( i in 0..10) {

            with(tempEmployeeList) {
                add(Employee(i * 10 + 0, " DfffV76"))
                add(Employee(i * 10 + 1, " Ivanov"))
                add(Employee(i * 10 + 2, " Petrov"))
                add(Employee(i * 10 + 3, " Sydorov"))
                add(Employee(i * 10 + 4, " R4"))
                add(Employee(i * 10 + 5, " Z56"))
                add(Employee(i * 10 + 6, " DCV76"))
                add(Employee(i * 10 + 7, " S6565v"))
                add(Employee(i * 10 + 8, " Roo4"))
                add(Employee(i * 10 + 9, " CCC6"))
            }
        }
        reload()

    }

    fun deleteEmployee(employee: Employee){
        tempEmployeeList.remove(employee)
        reload()
    }

    fun reload(){
        mutableEmployeeList.postValue(tempEmployeeList)
    }

}