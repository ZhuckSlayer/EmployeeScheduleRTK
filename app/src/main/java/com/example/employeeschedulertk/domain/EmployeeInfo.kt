package com.example.employeeschedulertk.domain

data class EmployeeInfo(
    val id:String,
    val firstName:String,
    val lastName:String,
    val surName:String,
    val phoneNumber:String,
    val address:String,
    val schedule:String,
    val age:Int,
    val experience:Int,
    val numberOfConnectedServices:Int,
    val numberOfAwaitingServices:Int,
    val salary:Double,
    val position:String,
    val numberConnectedInternet:Int,
    val numberConnectedSimCard:Int,
    val numberConnectedTv:Int,
    val numberConnectedVideo:Int
)
