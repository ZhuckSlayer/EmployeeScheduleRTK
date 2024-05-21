package com.example.employeeschedulertk.data.mapper

import com.example.employeeschedulertk.data.database.EmployeeDbModel
import com.example.employeeschedulertk.domain.EmployeeInfo
import com.google.firebase.database.DataSnapshot
import javax.inject.Inject

class EmployeeMapper @Inject constructor() {
    fun mapDbModelToEntity(db: EmployeeDbModel): EmployeeInfo {
        return EmployeeInfo(
            id = db.id,
            firstName = db.firstName,
            lastName = db.lastName,
            surName = db.surName,
            phoneNumber = db.phoneNumber,
            address = db.address,
            schedule = db.schedule,
            age = db.age,
            experience = db.experience,
            numberOfConnectedServices = db.numberOfConnectedServices,
            numberOfAwaitingServices = db.numberOfAwaitingServices,
            salary = db.salary,
            position = db.position,
            numberConnectedVideo = db.numberConnectedVideo,
            numberConnectedTv = db.numberConnectedTv,
            numberConnectedInternet = db.numberConnectedInternet,
            numberConnectedSimCard = db.numberConnectedSimCard

        )
    }

    fun mapDtoToEntity(snapshot: DataSnapshot): EmployeeInfo {
        return EmployeeInfo(
            id = snapshot.child(NODE_ID).value.toString(),
            firstName = snapshot.child(NODE_FIRST_NAME).value.toString(),
            lastName = snapshot.child(NODE_LAST_NAME).value.toString(),
            surName = snapshot.child(NODE_SURNAME).value.toString(),
            phoneNumber = snapshot.child(NODE_PHONE_NUMBER).value.toString(),
            address = snapshot.child(NODE_ADDRESS).value.toString(),
            schedule = snapshot.child(NODE_DAYS).value.toString(),
            age = snapshot.child(NODE_AGE).value.toString().toInt(),
            experience = snapshot.child(NODE_EXPERIENCE).value.toString().toInt(),
            numberOfConnectedServices = snapshot.child(NODE_NUMBER_OF_CONNECTED_SERVICES)
                .value
                .toString()
                .toInt(),
            numberOfAwaitingServices = snapshot.child(NODE_NUMBER_OF_AWAITING_SERVICES)
                .value
                .toString()
                .toInt(),
            salary = snapshot.child(NODE_SALARY).value.toString().toDouble(),
            position = snapshot.child(NODE_POSITION).value.toString(),
            numberConnectedVideo = snapshot.child(NODE_CONNECTED_VIDEO).value.toString().toInt(),
            numberConnectedTv = snapshot.child(NODE_CONNECTED_TELEVISION).value.toString().toInt(),
            numberConnectedInternet = snapshot.child(NODE_CONNECTED_INTERNET).value.toString()
                .toInt(),
            numberConnectedSimCard = snapshot.child(NODE_CONNECTED_SIMCARD).value.toString().toInt()
        )
    }

    fun mapDtoToDb(snapshot: DataSnapshot): EmployeeDbModel {
        return EmployeeDbModel(
            id = snapshot.child(NODE_ID).value.toString(),
            firstName = snapshot.child(NODE_FIRST_NAME).value.toString(),
            lastName = snapshot.child(NODE_LAST_NAME).value.toString(),
            surName = snapshot.child(NODE_SURNAME).value.toString(),
            phoneNumber = snapshot.child(NODE_PHONE_NUMBER).value.toString(),
            address = snapshot.child(NODE_ADDRESS).value.toString(),
            schedule = snapshot.child(NODE_DAYS).value.toString(),
            age = snapshot.child(NODE_AGE).value.toString().toInt(),
            experience = snapshot.child(NODE_EXPERIENCE).value.toString().toInt(),
            numberOfConnectedServices = snapshot.child(NODE_NUMBER_OF_CONNECTED_SERVICES)
                .value
                .toString()
                .toInt(),
            numberOfAwaitingServices = snapshot.child(NODE_NUMBER_OF_AWAITING_SERVICES)
                .value
                .toString()
                .toInt(),
            salary = snapshot.child(NODE_SALARY).value.toString().toDouble(),
            position = snapshot.child(NODE_POSITION).value.toString(),
            numberConnectedVideo = snapshot.child(NODE_CONNECTED_VIDEO).value.toString().toInt(),
            numberConnectedTv = snapshot.child(NODE_CONNECTED_TELEVISION).value.toString().toInt(),
            numberConnectedInternet = snapshot.child(NODE_CONNECTED_INTERNET).value.toString()
                .toInt(),
            numberConnectedSimCard = snapshot.child(NODE_CONNECTED_SIMCARD).value.toString().toInt()
        )
    }

    fun mapEntityToDb(employeeInfo: EmployeeInfo): EmployeeDbModel {
        return EmployeeDbModel(
            id = employeeInfo.id,
            firstName = employeeInfo.firstName,
            lastName = employeeInfo.lastName,
            surName = employeeInfo.surName,
            phoneNumber = employeeInfo.phoneNumber,
            address = employeeInfo.address,
            schedule = employeeInfo.schedule,
            age = employeeInfo.age,
            experience = employeeInfo.experience,
            numberOfConnectedServices = employeeInfo.numberOfConnectedServices,
            numberOfAwaitingServices = employeeInfo.numberOfAwaitingServices,
            salary = employeeInfo.salary,
            position = employeeInfo.position,
            numberConnectedVideo = employeeInfo.numberConnectedVideo,
            numberConnectedTv = employeeInfo.numberConnectedTv,
            numberConnectedInternet = employeeInfo.numberConnectedInternet,
            numberConnectedSimCard = employeeInfo.numberConnectedSimCard

        )
    }

    companion object {
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