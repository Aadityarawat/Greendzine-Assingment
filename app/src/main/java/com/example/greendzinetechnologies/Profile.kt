package com.example.greendzinetechnologies

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var searchEditText: EditText
    private var originalEmployeeList = mutableListOf<Employees>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText = view.findViewById(R.id.searchEditText)
        val color = ContextCompat.getColor(requireContext(), R.color.white)
        searchEditText.setHintTextColor(color)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Set layout manager
        adapter = MyAdapter()
        recyclerView.adapter = adapter

        loadJsonData()

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterEmployees(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterEmployees(query: String) {
        val filteredList = mutableListOf<Employees>()
        for (employee in originalEmployeeList) {
            if (employee.empname.contains(query, true)) {
                filteredList.add(employee)
            }
        }
        adapter.setEmployees(filteredList)

        // Scroll to the searchEditText after filtering
        recyclerView.scrollToPosition(10)
    }

    private fun loadJsonData() {
        val json : String?
        try{
            val inputStream : InputStream = requireContext().assets.open("emp.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonArray = JSONArray(json)
            val employeeList = mutableListOf<Employees>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val empID = jsonObject.getInt("EmpID")
                val name = jsonObject.getString("Name")
                val dob = jsonObject.getString("DOB")
                val role = jsonObject.getString("Role")
                val employee = Employees(empID, name, dob, role)
                employeeList.add(employee)
            }
            // Save the original list of employees
            originalEmployeeList.addAll(employeeList)

            // Update RecyclerView with the data list
            adapter.setEmployees(employeeList)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}