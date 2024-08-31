package com.example.employeeschedulertk.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeeschedulertk.data.network.FireBaseDataHelper
import com.example.employeeschedulertk.domain.GetEmployeeListUseCase
import com.example.employeeschedulertk.domain.GetEmployeeUseCase
import com.example.employeeschedulertk.domain.InsertEmployeeUseCase
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val getEmployeeListUseCase: GetEmployeeListUseCase,
    private val fireBaseDataHelper: FireBaseDataHelper,
    private val insertEmployeeUseCase: InsertEmployeeUseCase
) : ViewModel() {

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser>
        get() = _user

    private val _position = MutableLiveData<Boolean>()
    val position: LiveData<Boolean>
        get() = _position

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    fun signIn(email: String, password: String) {
        fireBaseDataHelper.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{}
        fireBaseDataHelper.firebaseAuth.createUserWithEmailAndPassword(email,password)
        val dateMap = mutableMapOf<String, Any>()
        dateMap[NODE_FIRST_NAME] = "Александр"
        dateMap[NODE_LAST_NAME] = "Михайлов"
        dateMap[NODE_SURNAME] = "Александрович"
        dateMap[NODE_PHONE_NUMBER] = "82340001212"
        dateMap[NODE_ADDRESS] = "Первомаечка"
        dateMap[NODE_DAYS] = "2,5,6,7,1,2"
        dateMap[NODE_AGE] = 18
        dateMap[NODE_EXPERIENCE] = 12
        dateMap[NODE_NUMBER_OF_CONNECTED_SERVICES] = 15
        dateMap[NODE_NUMBER_OF_AWAITING_SERVICES] = 12
        dateMap[NODE_CONNECTED_INTERNET] = 10
        dateMap[NODE_CONNECTED_TELEVISION] = 2
        dateMap[NODE_CONNECTED_SIMCARD] = 1
        dateMap[NODE_CONNECTED_VIDEO] = 0
        dateMap[NODE_SALARY] = 23000
        dateMap[NODE_POSITION] = "Администратор"
        dateMap[NODE_DECLINED_SERVICES] = 15
        dateMap[NODE_ID] = fireBaseDataHelper.firebaseAuth.currentUser?.uid.toString()
        fireBaseDataHelper.dataBaseReference.child("Employee")
            .child(fireBaseDataHelper.firebaseAuth.currentUser?.uid.toString())
            .updateChildren(dateMap).addOnCompleteListener{
                if (it.isSuccessful){
                    _isLoading.value=true
                }
                else{
                    _isLoading.value=false
                }
            }

        Log.d("TEST",fireBaseDataHelper.firebaseAuth.uid.toString())

    }

    init {
        val auth = fireBaseDataHelper.firebaseAuth

        auth.addAuthStateListener {
            if (it.currentUser != null) {
                _user.value = it.currentUser
            }
        }
    }

    companion object {
        /* val dateMap = mutableMapOf<String, Any>()
         dateMap[NODE_FIRST_NAME]="Александр"
         dateMap[NODE_LAST_NAME]="Михайлов"
         dateMap[NODE_SURNAME]="Александрович"
         dateMap[NODE_PHONE_NUMBER]="82340001212"
         dateMap[NODE_ADDRESS]="Первомаечка"
         dateMap[NODE_DAYS]="2,5,6,7,1,2"
         dateMap[NODE_AGE]=18
         dateMap[NODE_EXPERIENCE]=12
         dateMap[NODE_NUMBER_OF_CONNECTED_SERVICES]=15
         dateMap[NODE_NUMBER_OF_AWAITING_SERVICES]=12
         dateMap[NODE_CONNECTED_INTERNET]=10
         dateMap[NODE_CONNECTED_TELEVISION]=2
         dateMap[NODE_CONNECTED_SIMCARD]=1
         dateMap[NODE_CONNECTED_VIDEO]=0
         dateMap[NODE_SALARY]=23000
         dateMap[NODE_POSITION]="Стажер"
         dateMap[NODE_DECLINED_SERVICES]=15
         dateMap[NODE_ID]=fireBaseDataHelper.firebaseAuth.currentUser?.uid.toString()
         Log.d("Testqwe",fireBaseDataHelper.firebaseAuth.uid.toString())
         fireBaseDataHelper.dataBaseReference.child("Employee")
         .child(fireBaseDataHelper.firebaseAuth.currentUser?.uid.toString())
         .updateChildren(dateMap)*/

        private const val NODE_ID = "Id"
        const val NODE_EMAIL = "Email"
        const val NODE_FIRST_NAME = "FirstName"
        const val NODE_LAST_NAME = "LastName"
        const val NODE_SURNAME = "SurName"
        const val NODE_PHONE_NUMBER = "PhoneNumber"
        const val NODE_ADDRESS = "Address"
        const val NODE_DAYS = "Days"
        const val NODE_AGE = "Age"
        const val NODE_EXPERIENCE = "Experience"
        const val NODE_NUMBER_OF_CONNECTED_SERVICES = "NumberOfConnectedServices"
        const val NODE_NUMBER_OF_AWAITING_SERVICES = "NumberOfAwaitingServices"
        const val NODE_CONNECTED_INTERNET = "NumberConnectedInternet"
        const val NODE_CONNECTED_TELEVISION = "NumberConnectedTelevision"
        const val NODE_CONNECTED_SIMCARD = "NumberConnectedSimCard"
        const val NODE_CONNECTED_VIDEO = "NumberConnectedVideo"
        const val NODE_SALARY = "Salary"
        const val NODE_POSITION = "Position"
        const val NODE_DECLINED_SERVICES = "DeclinedServices"
    }


}