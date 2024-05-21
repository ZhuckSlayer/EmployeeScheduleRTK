package com.example.employeeschedulertk.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.GetEmployeeUseCase
import com.example.employeeschedulertk.domain.InsertEmployeeUseCase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val fireBaseDataHelper: FireBaseDataHelper,
    private val insertEmployeeUseCase: InsertEmployeeUseCase,
    private val getEmployeeUseCase: GetEmployeeUseCase
) : ViewModel() {

    private val _user = MutableLiveData<EmployeeInfo>()
    val user: LiveData<EmployeeInfo>
        get() = _user

    init {
//        fireBaseDataHelper.firebaseAuth.signOut()
        fireBaseDataHelper
            .dataBaseReference
            .child("Employee")
            .child(fireBaseDataHelper.firebaseAuth.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("Test2", "Заходит")
                    viewModelScope.launch {
                        insertEmployeeUseCase(snapshot)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }
    fun getDataUser()=getEmployeeUseCase(fireBaseDataHelper.firebaseAuth.uid.toString())


}