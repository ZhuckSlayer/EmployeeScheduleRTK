package com.example.employeeschedulertk.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.presentation.login.LoginViewModel.Companion.NODE_POSITION
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val fireBaseDataHelper: FireBaseDataHelper
) : ViewModel() {

    private val _user= MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?>
        get() = _user

    private val _position=MutableLiveData<Boolean>()
    val position:LiveData<Boolean>
        get() = _position

    init {
        val auth=fireBaseDataHelper.firebaseAuth
        fireBaseDataHelper.dataBaseReference.child("Employee")
            .child(auth.currentUser?.uid.toString()).get().addOnSuccessListener {
                _position.value = it.child(NODE_POSITION).value.toString() == "Стажер"
            }
        auth.addAuthStateListener {
            if (it.currentUser!=null){
                _user.value=it.currentUser
            }else{
                _user.value=null
            }
        }
    }


}