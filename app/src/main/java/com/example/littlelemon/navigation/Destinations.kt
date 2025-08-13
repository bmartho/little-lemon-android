package com.example.littlelemon.navigation

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route: String = "Home"
}

object Profile : Destinations {
    override val route: String = "Profile"
}

object Onboarding : Destinations {
    override val route: String = "Onboarding"
}