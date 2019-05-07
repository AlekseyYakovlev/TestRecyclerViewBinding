package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.data.EmployeeRepo
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.utils.toast

class EmployeeViewModel : ViewModel() {

    val employeeList = EmployeeRepo.employeeList


    fun onItemClick(context: Context?, employee: Employee){
        context?.toast("U picked ${employee.name}!")
        EmployeeRepo.deleteEmployee(employee)
    }


}