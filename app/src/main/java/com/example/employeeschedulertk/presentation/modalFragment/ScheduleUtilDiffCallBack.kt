package com.example.employeeschedulertk.presentation.modalFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.employeeschedulertk.domain.EmployeeInfo

class ScheduleUtilDiffCallBack:DiffUtil.ItemCallback<EmployeeInfo>() {
    override fun areItemsTheSame(oldItem: EmployeeInfo, newItem: EmployeeInfo): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: EmployeeInfo, newItem: EmployeeInfo): Boolean {
        return oldItem==newItem
    }
}