package com.joohnq.propertiesrentalapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.propertiesrentalapp.view.components.CustomSnackBarHost
import com.joohnq.propertiesrentalapp.view.theme.PropertiesRentalAppTheme
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import com.joohnq.propertiesrentalapp.viewmodel.UserViewModel


@Composable
fun HomeScreen(userViewModel: UserViewModel?, navController: NavController) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    PropertiesRentalAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            snackbarHost = { CustomSnackBarHost(snackBarHostState) },
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Button(onClick = { userViewModel?.logout() }) {
                    Text("Sair")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(null, navController)
}