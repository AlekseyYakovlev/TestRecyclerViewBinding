package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.data.EmployeeRepo
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.utils.toast

class EmployeeViewModel : ViewModel() {

    val employeeList = EmployeeRepo.employeeList


    fun onItemClick(context: Context? = null, employee: Employee) {
        context?.toast("U picked ${employee.name}!")
    }

    fun onItemsSwap(context: Context? = null, employee1: Employee?, employee2: Employee?) {
        if (employee1 != null && employee2 != null) {
            context?.toast("${employee1.name} and ${employee2.name} swapped!")
            EmployeeRepo.swap(employee1, employee2)
        }
    }

    fun onItemSwiped(context: Context? = null, employee: Employee?) {
        if (employee != null) {
            context?.toast("${employee.name} is deleted!")
            EmployeeRepo.delete(employee)
        }
    }
}