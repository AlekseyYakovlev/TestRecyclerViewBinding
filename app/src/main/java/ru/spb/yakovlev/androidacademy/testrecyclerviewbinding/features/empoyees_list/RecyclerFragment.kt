package ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.R
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.common.Employee
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.databinding.FragmentRecyclerBinding
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list.views.EmployeeAdapter
import ru.spb.yakovlev.androidacademy.testrecyclerviewbinding.features.empoyees_list.views.EmployeeHolder


class RecyclerFragment : Fragment(),
    EmployeeAdapter.OnItemClickListener {

    private val defaultColumnCount = 2

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var binding: FragmentRecyclerBinding


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
        val columnCount = (activity?.resources?.getInteger(R.integer.column_count)) ?: defaultColumnCount
        val employeeAdapter = EmployeeAdapter(viewModel, this@RecyclerFragment)

        viewModel.employeeList.observe(
            this@RecyclerFragment, Observer { list -> employeeAdapter.updateData(list) })

        val dragDirs: Int
        val swipeDirs: Int
        when (columnCount) {
            1 -> {
                dragDirs = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeDirs = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            }
            else -> {
                dragDirs = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                swipeDirs = 0
            }
        }
        val itemTouchHelper = ItemTouchHelper(getItemTouchHelperSimpleCallback(dragDirs, swipeDirs))


        with(binding.employeeListRV) {

            layoutManager = when (columnCount) {
                1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            //TODO: Add custom animation
            itemAnimator = DefaultItemAnimator()

            itemTouchHelper.attachToRecyclerView(this@with)
            adapter = employeeAdapter
        }
    }

    private fun getItemTouchHelperSimpleCallback(dragDirs: Int, swapDirs: Int) =
        object : ItemTouchHelper.SimpleCallback(dragDirs, swapDirs) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val item1 = (viewHolder as EmployeeHolder).binding.employee
                val item2 = (target as EmployeeHolder).binding.employee
                viewModel.onItemsSwap(this@RecyclerFragment.context, item1, item2)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val item = (viewHolder as EmployeeHolder).binding.employee
                viewModel.onItemSwiped(this@RecyclerFragment.context, item)
            }
        }

    override fun onItemClicked(employee: Employee) {
        viewModel.onItemClick(this.context, employee)
    }
}
