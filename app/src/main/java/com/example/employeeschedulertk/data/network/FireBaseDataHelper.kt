package com.example.employeeschedulertk.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FireBaseDataHelper @Inject constructor() {
    val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    val dataBaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }
}