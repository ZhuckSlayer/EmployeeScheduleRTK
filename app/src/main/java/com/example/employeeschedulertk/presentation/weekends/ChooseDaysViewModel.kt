package com.example.employeeschedulertk.presentation.weekends

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.GetEmployeeListUseCase
import com.example.employeeschedulertk.domain.InsertEmployeeUseCase
import com.example.employeeschedulertk.domain.UpdateEmployeeScheduleUseCase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChooseDaysViewModel @Inject constructor(
    private val fireBaseDataHelper: FireBaseDataHelper,
    private val updateEmployeeScheduleUseCase: UpdateEmployeeScheduleUseCase,
    private val getEmployeeListUseCase: GetEmployeeListUseCase
) : ViewModel() {


    private val _testList = MutableLiveData<Int>()
    val testList: LiveData<Int>
        get() = _testList

    fun insertSchedule(schedule: List<String>) {
        val user = fireBaseDataHelper.firebaseAuth.currentUser?.uid.toString()
        updateEmployeeScheduleUseCase(user, schedule)
    }

    fun getScheduleOnDay() {
        val mapTest = mutableMapOf<String, List<Int>>()
        fireBaseDataHelper.dataBaseReference.child("Employee")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        fireBaseDataHelper.dataBaseReference.child("Employee")
                            .child(it.key.toString())
                            .child("Days")
                            .get()
                            .addOnSuccessListener { its ->
                                val value = its.value.toString().filter { it.isDigit() }


                            }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
    val test = getEmployeeListUseCase()
}