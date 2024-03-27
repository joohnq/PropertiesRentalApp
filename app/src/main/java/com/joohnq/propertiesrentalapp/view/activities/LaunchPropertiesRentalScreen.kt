package com.joohnq.propertiesrentalapp.view.activities

import UiState
import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.commandiron.compose_loading.Circle
import com.google.android.gms.location.FusedLocationProviderClient
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.model.entities.AutoCompleteRequest
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.activities.ui.theme.PropertiesRentalAppTheme
import com.joohnq.propertiesrentalapp.view.components.CustomSnackBarHost
import com.joohnq.propertiesrentalapp.view.components.MainBottomNavigation
import com.joohnq.propertiesrentalapp.view.components.PermissionDialog
import com.joohnq.propertiesrentalapp.view.screens.ChatScreen
import com.joohnq.propertiesrentalapp.view.screens.ExploreScreen
import com.joohnq.propertiesrentalapp.view.screens.HomeScreen
import com.joohnq.propertiesrentalapp.view.screens.LoadingScreen
import com.joohnq.propertiesrentalapp.view.screens.LoginScreen
import com.joohnq.propertiesrentalapp.view.screens.PresentationScreen
import com.joohnq.propertiesrentalapp.view.screens.ProfileScreen
import com.joohnq.propertiesrentalapp.view.screens.RegisterScreen
import com.joohnq.propertiesrentalapp.view.screens.SavedScreen
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC
import com.joohnq.propertiesrentalapp.view.theme.Purple6246EA
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.LocationViewModel
import com.joohnq.propertiesrentalapp.viewmodel.MainViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PermissionsViewModel
import com.joohnq.propertiesrentalapp.viewmodel.PropertyViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LaunchPropertiesRentalScreen(
    activity: Activity,
    openAppSettings: () -> Unit,
    fusedLocationClient: FusedLocationProviderClient,
    mainViewModel: MainViewModel,
    authViewModel: AuthViewModel,
    permissionsViewModel: PermissionsViewModel,
    locationViewModel: LocationViewModel,
    userViewModel: UserViewModel,
    propertyViewModel: PropertyViewModel,
    googleAuthUiClient: GoogleAuthUiClient,
    executeMultiplePermission: () -> Unit = { },
    launcher: ActivityResultLauncher<IntentSenderRequest>,
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val currentNavDestination = remember { mutableStateOf(navController.currentDestination?.route) }
    var selectedItemBottomBar by rememberSaveable { mutableIntStateOf(0) }
    val onChangeBottomBar = { index: Int -> selectedItemBottomBar = index }
    val dialogQueue = permissionsViewModel.visiblePermissionDialogQueue
    val user by userViewModel.user.collectAsState()
    val startDestination = Screen.LoadingScreen.rout
    val coroutineScope = rememberCoroutineScope()
    val permitBottomBar = listOf(
        Screen.HomeScreen.rout,
        Screen.ExploreScreen.rout,
        Screen.ChatScreen.rout,
        Screen.SavedScreen.rout,
        Screen.ProfileScreen.rout
    )
    val locationName by locationViewModel.locationName.collectAsState()
    val location by locationViewModel.location.collectAsState()
    val autoCompleteLocationNearYourLocation by propertyViewModel.autoCompleteLocationNearYourLocation.collectAsState()
    val autoCompleteLocationTopRated by propertyViewModel.autoCompleteLocationTopRated.collectAsState()
    val nearYourLocation by propertyViewModel.nearYourLocationProperties.collectAsState()
    val topRated by propertyViewModel.topRatedProperties.collectAsState()
    val rentOrBuy by mainViewModel.rentOrBuy.collectAsState()
    val onChangeRentOrBuy = { i: Int -> mainViewModel.setRentOrBuy(i) }
    val error = remember { mutableStateOf("") }
    val onErrorAction = { s: String -> error.value = s }


    LaunchedEffect(error) {
        if (error.value.isNotEmpty()) {
            scope.launch {
                snackBarHostState.showSnackbar(error.value)
            }
        }
    }

    dialogQueue.reversed().forEach { permission ->
        PermissionDialog(
            permission = permission,
            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                activity,
                permission
            ),
            onDismiss = {
                permissionsViewModel.dismissDialog()
            },
            onOkClick = {
                permissionsViewModel.dismissDialog()
                executeMultiplePermission()
            },
            onGoToAppSettingsClick = openAppSettings
        )
    }

    LaunchedEffect(Unit) {
        userViewModel.getUserFromDatabase()
        executeMultiplePermission()
        propertyViewModel.getAutoComplete()
    }

    LaunchedEffect(location) {
        val locationSubAdminArea = location?.subAdminArea
        locationSubAdminArea?.run { propertyViewModel.getAutoComplete(locationSubAdminArea) }
    }

    when (val state = autoCompleteLocationTopRated) {
        is UiState.Success -> {
            LaunchedEffect(Unit) {
                println("Executouuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu")
                state.data?.let { autoCompleteRequest: AutoCompleteRequest ->
                    val autoComplete = autoCompleteRequest.data?.autocomplete
                    val id = autoComplete?.get(0)?.id ?: ""
                    propertyViewModel.getTopRatedProperties(id)
                }
            }
        }

        is UiState.Loading -> {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .heightIn(min = 100.dp)
                    .fillMaxWidth()
            ) {
                Circle(size = 30.dp, color = Purple6246EA)
            }
        }

        is UiState.Failure -> {
            println("errooooooorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" + state.error)
            state.error?.let { e ->
                onErrorAction(e)
            }
        }

        else -> Unit
    }
    when (val state = autoCompleteLocationNearYourLocation) {
        is UiState.Success -> {
            LaunchedEffect(Unit) {
                println("Executouuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu")
                state.data?.let { autoCompleteRequest: AutoCompleteRequest ->
                    val autoComplete = autoCompleteRequest.data?.autocomplete
                    val id = autoComplete?.get(0)?.id ?: ""
                    propertyViewModel.getNearYourLocationProperties(id)
                }
            }
        }

        is UiState.Loading -> {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .heightIn(min = 100.dp)
                    .fillMaxWidth()
            ) {
                Circle(size = 30.dp, color = Purple6246EA)
            }
        }

        is UiState.Failure -> {
            println("errooooooorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr2" + state.error)
            state.error?.let { e ->
                onErrorAction(e)
            }
        }

        else -> Unit
    }

    PropertiesRentalAppTheme {
        Scaffold(
            snackbarHost = { CustomSnackBarHost(snackBarHostState) },
            modifier = Modifier
                .fillMaxSize()
                .background(color = GrayFCFCFC)
                .padding(16.dp),
            bottomBar = {
                if (permitBottomBar.contains(currentNavDestination.value))
                    MainBottomNavigation(
                        navController = navController,
                        selectedItem = selectedItemBottomBar,
                        onChangeBottomBar = onChangeBottomBar
                    )
            }
        ) { padding ->
            NavHost(
                navController = navController, startDestination = startDestination
            ) {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    currentNavDestination.value = destination.route ?: ""
                }
                composable(Screen.PresentationScreen.rout) {
                    PresentationScreen(
                        padding = padding,
                        authViewModel = authViewModel,
                        navController = navController,
                    )
                }
                composable(Screen.LoginScreen.rout) {
                    LoginScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        authViewModel = authViewModel,
                        googleAuthUiClient = googleAuthUiClient,
                        launcher = launcher,
                        navController = navController
                    )
                }
                composable(Screen.RegisterScreen.rout) {
                    RegisterScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        authViewModel = authViewModel,
                        googleAuthUiClient = googleAuthUiClient,
                        launcher = launcher,
                        navController = navController
                    )
                }
                composable(Screen.HomeScreen.rout) {
                    HomeScreen(
                        rentOrBuy = rentOrBuy,
                        location = locationName,
                        nearYourLocation = nearYourLocation,
                        autoCompleteLocation = autoCompleteLocationTopRated,
                        context = activity,
                        topRated = topRated,
                        scope = scope,
                        fusedLocationClient = fusedLocationClient,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        mainViewModel = mainViewModel,
                        userViewModel = userViewModel,
                        locationViewModel = locationViewModel,
                        propertyViewModel = propertyViewModel,
                        navController = navController,
                        onChangeRentOrBuy = onChangeRentOrBuy,
                        onErrorAction = onErrorAction
                    )
                }
                composable(Screen.LoadingScreen.rout) {
                    LoadingScreen()
                }
                composable(Screen.ExploreScreen.rout) {
                    ExploreScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        navController = navController,
                    )
                }
                composable(Screen.ChatScreen.rout) {
                    ChatScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        navController = navController,
                    )
                }
                composable(Screen.SavedScreen.rout) {
                    SavedScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        navController = navController,
                    )
                }
                composable(Screen.ProfileScreen.rout) {
                    ProfileScreen(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        padding = padding,
                        navController = navController,
                    )
                }
            }
        }
    }

    LaunchedEffect(user) {
        coroutineScope.launch {
            userViewModel.user.collectLatest { user ->
                navController.navigate(
                    when (user) {
                        is UiState.Loading -> Screen.LoadingScreen.rout
                        is UiState.Success -> Screen.HomeScreen.rout
                        is UiState.Failure -> {
                            Screen.PresentationScreen.rout
                        }

                        else -> Screen.PresentationScreen.rout
                    }
                )
            }
        }
    }

}