package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.databinding.EmployeeItemBinding
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list.EmployeeViewModel

class EmployeeAdapter(
    private val employeeViewModel: EmployeeViewModel,
    private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<EmployeeHolder>() {

    private val itemsList = mutableListOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EmployeeItemBinding.inflate(inflater, parent, false)
        return EmployeeHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { v ->
            employeeViewModel.onItemClick(v.context, item)
            //onItemClickListener.onItemClicked(item)
        }
    }

    override fun getItemCount() = itemsList.size

    fun updateData(newItems: List<Employee>) {
        val diffCallback =
            EmployeeItemDiffCallback(
                itemsList,
                newItems
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemsList.clear()
        itemsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }


    interface OnItemClickListener {
        fun onItemClicked(employee: Employee)
    }
}