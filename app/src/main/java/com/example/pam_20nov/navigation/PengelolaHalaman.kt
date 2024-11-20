package com.example.pam_20nov.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pam_20nov.ui.view.screen.MahasiswaFormView
import com.example.pam_20nov.ui.view.screen.RencanaStudyView
import com.example.pam_20nov.ui.view.screen.SplashView
import com.example.pam_20nov.ui.view.screen.TampilView
import com.example.pam_20nov.ui.view.viewmodel.MahasiswaViewModel
import com.example.pam_20nov.ui.view.viewmodel.RencanaStudyViewModel

enum class Halaman {
    Splash,
    Mahasiswa,
    Peminatan,
    TampilKrs
}

@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    RencanaStudyViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val mahasiswaUiState = mahasiswaViewModel.mahasiswaUiState.collectAsState().value
    val rencanaStudiUiState = RencanaStudyViewModel.krsStateUi.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = modifier.padding()
    ) {
        composable(
            route = Halaman.Splash.name
        ){
            SplashView (
                onMulaiButton = {
                    navController.navigate(Halaman.Mahasiswa.name)
            })
        }

        composable(route = Halaman.Mahasiswa.name) {
            MahasiswaFormView(
                onSubmitButton = {
                    mahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.Peminatan.name)},
                onbackbuttonClicked = {navController.popBackStack()}
            )
        }

        composable(route = Halaman.Peminatan.name) {
            RencanaStudyView(
                mahasiswa = mahasiswaUiState,
                onSubmitButton = {
                    RencanaStudyViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.TampilKrs.name)
                },
                onbackbuttonClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Halaman.TampilKrs.name) {
            TampilView(
                mahasiswa = mahasiswaUiState,
                krs = rencanaStudiUiState
            )
        }

    }
}