package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features

import androidx.recyclerview.widget.DiffUtil
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee

class EmployeeItemDiffCallback (private val oldList: List<Employee>,
                                private val newList: List<Employee>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]

}