package com.example.employeeschedulertk.presentation.modalFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.GetEmployeeUseCase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModalFragmentScheduleViewModel @Inject constructor(
    private val fireBaseDataHelper: FireBaseDataHelper,
    private val getEmployeeUseCase: GetEmployeeUseCase
) : ViewModel() {

    private val coroutineScope= CoroutineScope(Dispatchers.IO)

    private val _testList = MutableLiveData<List<EmployeeInfo>>()
    val testList: LiveData<List<EmployeeInfo>>
        get() = _testList

    fun getScheduleOnDay(day: Int) {
        val listId = mutableListOf<EmployeeInfo>()
        fireBaseDataHelper.dataBaseReference.child("Employee")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        fireBaseDataHelper.dataBaseReference.child("Employee")
                            .child(it.key.toString())
                            .child("Days")
                            .get()
                            .addOnSuccessListener { its ->
                                val value = listOf(its.value.toString().split(","))
                                for (i in value) {
                                    val test = i.map {
                                        it.replace(Regex("[^0-9]"), "").toInt()
                                    }
                                    coroutineScope.launch {
                                        if (test.contains(day)) {
                                            listId.add(getEmployeeUseCase(it.key.toString()))
                                            viewModelScope.launch(Dispatchers.Main) {
                                                _testList.value = listId
                                            }

                                        }
                                    }

                                }
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


    }
}