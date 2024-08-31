package com.example.effectivemobileapplication.features.tickets.searchtickets.presentation

interface LoginScreenRouter {
    fun openHardRoute()
    fun openCalendar()
    fun openFreeTickets()
    fun openTicketsToCountry(cityName:String)
    fun openTicketsOffers(cityDeparture:String,cityArrive:String)
}