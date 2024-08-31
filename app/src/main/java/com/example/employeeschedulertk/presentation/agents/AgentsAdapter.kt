package com.example.employeeschedulertk.presentation.agents

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.AgentCardBinding
import com.example.employeeschedulertk.databinding.FragmentAgentsBinding
import com.example.employeeschedulertk.domain.EmployeeInfo
import java.util.Locale
import kotlin.coroutines.coroutineContext

class AgentsAdapter :
    ListAdapter<EmployeeInfo, AgentsAdapter.AgentViewHolder>(AgentUtilDiffCallBack()) {

    class AgentViewHolder(val binding: AgentCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding =
            AgentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val binding = holder.binding
        val employeeInfo = getItem(position)

        binding.personInformation.text = holder.itemView.context.getString(
            R.string.name_surname,
            employeeInfo.lastName,
            employeeInfo.firstName
        )
        binding.schedule.text = employeeInfo.schedule
    }
}