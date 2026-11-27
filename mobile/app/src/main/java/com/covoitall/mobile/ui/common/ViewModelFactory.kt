package com.covoitall.mobile.ui.common

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.covoitall.mobile.AppContainer
import com.covoitall.mobile.CovoitallApp
import com.covoitall.mobile.ui.auth.AuthViewModel
import com.covoitall.mobile.ui.profile.ProfileViewModel
import com.covoitall.mobile.ui.rides.MyReservationsViewModel
import com.covoitall.mobile.ui.rides.RideSearchViewModel

/** Fabrique des ViewModels de l'espace passager (Zaara). */
class ViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(container) {
        when (modelClass) {
            AuthViewModel::class.java -> AuthViewModel(authRepository)
            ProfileViewModel::class.java -> ProfileViewModel(authRepository)
            RideSearchViewModel::class.java -> RideSearchViewModel(rideRepository, reservationRepository)
            MyReservationsViewModel::class.java -> MyReservationsViewModel(reservationRepository)
            else -> throw IllegalArgumentException("ViewModel inconnu : ${modelClass.name}")
        } as T
    }
}

inline fun <reified VM : ViewModel> ComponentActivity.appViewModels(): Lazy<VM> =
    viewModels { ViewModelFactory((application as CovoitallApp).container) }
