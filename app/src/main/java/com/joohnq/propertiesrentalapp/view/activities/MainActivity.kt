package com.joohnq.propertiesrentalapp.view.activities

import UiState
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.view.components.PermissionDialog
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PermissionsViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PropertyViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var googleAuthUiClient: GoogleAuthUiClient

    @Inject
    lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    private val mainViewModel by viewModels<MainViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()
    private val permissionsViewModel by viewModels<PermissionsViewModel>()
    private val locationViewModel by viewModels<LocationViewModel>()
    private val propertyViewModel by viewModels<PropertyViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var multiplePermissionResultLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var launcher: ActivityResultLauncher<IntentSenderRequest>

    private fun handleGoogleAuthResult(result: ActivityResult, changeGoogleState: () -> Unit) {
        if (result.resultCode == Activity.RESULT_OK) {
            lifecycleScope.launch {
                val signInResult =
                    googleAuthUiClient.signInWithIntent(intent = result.data ?: return@launch)
                authViewModel.authUserWithGoogle(signInResult)
            }
        } else changeGoogleState()
    }

    @Composable
    fun RequestPermissionIfNeeded() {
        val visiblePermissionDialogQueue = permissionsViewModel.visiblePermissionDialogQueue
        if (permissionsToRequest.any { permission ->
                ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            }) {
            SideEffect {
                requestPermissions()
            }
            visiblePermissionDialogQueue.forEach {
                PermissionDialog(
                    permission = it,
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(it),
                    onDismiss = permissionsViewModel::dismissDialog,
                    onOkClick = {
                        requestPermissions()
                    },
                    onGoToAppSettingsClick = {
                        openAppSettings()
                    }
                )
            }
        } else {
            InitializeScreen()
        }
    }

    private fun requestPermissions() {
        multiplePermissionResultLauncher.launch(permissionsToRequest)
    }

    private fun handlePermissionResult(perms: Map<String, Boolean>) {
        permissionsToRequest.forEach { perm ->
            permissionsViewModel.onPermissionResult(
                permission = perm, isGranted = perms[perm] == true
            )
        }
    }

    @Composable
    fun InitializeScreen() {
        launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            handleGoogleAuthResult(result) {
                authViewModel.setGoogle(UiState.None)
            }
        }

        LaunchPropertiesRentalScreen(
            activity = this,
            fusedLocationClient = fusedLocationClient,
            openAppSettings = ::openAppSettings,
            mainViewModel = mainViewModel,
            authViewModel = authViewModel,
            permissionsViewModel = permissionsViewModel,
            locationViewModel = locationViewModel,
            userViewModel = userViewModel,
            propertyViewModel = propertyViewModel,
            googleAuthUiClient = googleAuthUiClient,
            launcher = launcher
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            multiplePermissionResultLauncher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { perms ->
                    handlePermissionResult(perms)
                }

            RequestPermissionIfNeeded()
        }
    }

}
