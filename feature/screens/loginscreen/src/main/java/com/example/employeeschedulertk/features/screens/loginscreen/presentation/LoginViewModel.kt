package com.example.effectivemobileapplication.features.tickets.searchtickets.presentation

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val router: LoginScreenRouter
):ViewModel() {

    fun navigate(cityDeparture:String,cityArrive:String){
        router.openTicketsOffers(cityDeparture,cityArrive)
    }

    fun openHardRoute(){
        router.openHardRoute()
    }
    fun openWeekends(){
        router.openCalendar()
    }
    fun openFireTickets(){
        router.openFreeTickets()
    }

}