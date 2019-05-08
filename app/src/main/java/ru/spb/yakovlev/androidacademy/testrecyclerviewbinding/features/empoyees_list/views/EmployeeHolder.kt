package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list.views

import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.databinding.EmployeeItemBinding

class EmployeeHolder(val binding: EmployeeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(employee: Employee) {
        binding.employee = employee
        binding.executePendingBindings()
    }
}