package com.example.employeeschedulertk.presentation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeschedulertk.data.mapper.EmployeeMapper
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.example.employeeschedulertk.domain.GetEmployeeUseCase
import com.example.employeeschedulertk.domain.InsertEmployeeUseCase
import com.example.employeeschedulertk.presentation.login.LoginViewModel.Companion.NODE_POSITION
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val fireBaseDataHelper: FireBaseDataHelper,
    private val insertEmployeeUseCase: InsertEmployeeUseCase,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val mapper: EmployeeMapper
) : ViewModel() {

    private val _user = MutableSharedFlow<EmployeeInfo>()
    val user = _user.asSharedFlow()

    private val _authorUser = MutableSharedFlow<EmployeeInfo>()
    val authorUser = _authorUser.asSharedFlow()

    private val _position = MutableLiveData<Boolean>()
    val position: LiveData<Boolean>
        get() = _position

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun signOut() {
        fireBaseDataHelper.firebaseAuth.signOut()
    }

    init {
        _loading.value = true
        val auth = fireBaseDataHelper.firebaseAuth
        fireBaseDataHelper.dataBaseReference.child("Employee")
            .child(auth.currentUser?.uid.toString()).get().addOnSuccessListener {
                _position.value = it.child(NODE_POSITION).value.toString().trim() != "Стажер"
            }

        fireBaseDataHelper
            .dataBaseReference
            .child("Employee")
            .child(fireBaseDataHelper.firebaseAuth.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    viewModelScope.launch {
                        insertEmployeeUseCase(snapshot)
                        _user.emit(mapper.mapDtoToEntity(snapshot))
                        _loading.value = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _loading.value = false
                }
            })
    }

    fun getDataUser() = getEmployeeUseCase(
        fireBaseDataHelper
            .firebaseAuth
            .uid
            .toString()
    )


}