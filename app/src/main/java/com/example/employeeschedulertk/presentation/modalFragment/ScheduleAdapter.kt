package com.example.employeeschedulertk.presentation.modalFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeschedulertk.R
import com.example.employeeschedulertk.databinding.AgentCardBinding
import com.example.employeeschedulertk.domain.EmployeeInfo

class ScheduleAdapter :
    ListAdapter<EmployeeInfo, ScheduleAdapter.ScheduleViewHolder>(ScheduleUtilDiffCallBack()) {

    class ScheduleViewHolder(val binding: AgentCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding =
            AgentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

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