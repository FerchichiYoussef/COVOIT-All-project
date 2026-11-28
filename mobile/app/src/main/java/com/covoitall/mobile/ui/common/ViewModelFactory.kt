package com.covoitall.mobile.ui.common

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covoitall.mobile.AppContainer
import com.covoitall.mobile.CovoitallApp
import com.covoitall.mobile.ui.auth.AuthViewModel
import com.covoitall.mobile.ui.lines.LineDetailViewModel
import com.covoitall.mobile.ui.lines.LinesViewModel
import com.covoitall.mobile.ui.profile.ProfileViewModel

/** Fabrique des ViewModels de l'espace navettes/lignes/GPS (Youssef). */
import com.covoitall.mobile.ui.driver.DriverRidesViewModel
import com.covoitall.mobile.ui.driver.RideFormViewModel
import com.covoitall.mobile.ui.driver.RideReservationsViewModel
import com.covoitall.mobile.ui.profile.ProfileViewModel

/** Fabrique des ViewModels de l'espace conducteur (Mohamed Saif). */
class ViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(container) {
        when (modelClass) {
            AuthViewModel::class.java -> AuthViewModel(authRepository)
            ProfileViewModel::class.java -> ProfileViewModel(authRepository)
            LinesViewModel::class.java -> LinesViewModel(lineRepository)
            LineDetailViewModel::class.java -> LineDetailViewModel(lineRepository)
            DriverRidesViewModel::class.java -> DriverRidesViewModel(rideRepository, authRepository)
            RideFormViewModel::class.java -> RideFormViewModel(rideRepository)
            RideReservationsViewModel::class.java -> RideReservationsViewModel(rideRepository)
            else -> throw IllegalArgumentException("ViewModel inconnu : ${modelClass.name}")
        } as T
    }
}

inline fun <reified VM : ViewModel> ComponentActivity.appViewModels(): Lazy<VM> =
    viewModels { ViewModelFactory((application as CovoitallApp).container) }
