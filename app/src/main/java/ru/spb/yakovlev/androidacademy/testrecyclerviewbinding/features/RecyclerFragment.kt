package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.R
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment(), EmployeeAdapter.OnItemClickListener {

    private val defaultColumnCount = 2

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var employeeAdapter: EmployeeAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        employeeAdapter = EmployeeAdapter(viewModel, this@RecyclerFragment)
        viewModel.employeeList.observe(
            this@RecyclerFragment, Observer { list -> employeeAdapter.updateData(list) })

        val columnCount = (activity?.resources?.getInteger(R.integer.column_count)) ?: defaultColumnCount

        with(binding.employeeListRV) {

            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            //TODO: Add custom animation
            itemAnimator = DefaultItemAnimator()
            adapter = employeeAdapter
        }
    }

    override fun onItemClicked(employee: Employee) {
        viewModel.onItemClick(this.context, employee)
    }
}
