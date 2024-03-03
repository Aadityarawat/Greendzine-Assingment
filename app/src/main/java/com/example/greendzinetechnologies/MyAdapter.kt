package com.example.greendzinetechnologies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var employeeList = mutableListOf<Employees>()
    private var filteredEmployeeList = mutableListOf<Employees>()

    fun setEmployees(employees: List<Employees>) {
        employeeList.clear()
        employeeList.addAll(employees)
        filteredEmployeeList.clear()
        filteredEmployeeList.addAll(employees)
        notifyDataSetChanged()
    }

    fun filterList(filteredList: List<Employees>) {
        filteredEmployeeList.clear()
        filteredEmployeeList.addAll(filteredList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewEmpId: TextView = itemView.findViewById(R.id.empid)
        private val textViewName: TextView = itemView.findViewById(R.id.empname)
        private val textViewDOB: TextView = itemView.findViewById(R.id.empdob)
        private val textViewRole: TextView = itemView.findViewById(R.id.empRole)
        private val sideid : TextView = itemView.findViewById(R.id.sideid)

        fun bind(employee: Employees) {
            sideid.text = "${employee.empidd}"
            textViewEmpId.text = "${employee.empidd}"
            textViewName.text = "${employee.empname}"
            textViewDOB.text = "${employee.empdob}"
            textViewRole.text = "${employee.emprole}"
        }
    }
}