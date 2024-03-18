package com.joohnq.propertiesrentalapp.view.screens

import UiState
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.google.GoogleAuthUiClient
import com.joohnq.propertiesrentalapp.util.Origin
import com.joohnq.propertiesrentalapp.util.TextInputVerification.verifyFields
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.components.GoogleFilledButton
import com.joohnq.propertiesrentalapp.view.components.GradientFilledButtonLarge
import com.joohnq.propertiesrentalapp.view.components.LoadingPage
import com.joohnq.propertiesrentalapp.view.components.OrDivider
import com.joohnq.propertiesrentalapp.view.components.TextFieldEmail
import com.joohnq.propertiesrentalapp.view.components.TextFieldPassword
import com.joohnq.propertiesrentalapp.view.components.h1_24_bold_fs
import com.joohnq.propertiesrentalapp.view.components.p_16_normal_fs
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    scope: CoroutineScope = rememberCoroutineScope(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    padding: PaddingValues = PaddingValues(16.dp),
    authViewModel: AuthViewModel?,
    googleAuthUiClient: GoogleAuthUiClient?,
    launcher: ActivityResultLauncher<IntentSenderRequest>? = null,
    navController: NavController = rememberNavController()
) {
    val register = authViewModel?.register?.collectAsState()?.value
    val google = authViewModel?.google?.collectAsState()?.value

    var email: String by remember { mutableStateOf("") }
    val onEmailChange = { e: String -> email = e }
    var emailErrorText: String by remember { mutableStateOf("") }

    var emailError: Boolean by remember { mutableStateOf(false) }
    val onEmailErrorChange = { e: Boolean, msn: String ->
        emailError = e
        emailErrorText = msn
    }

    var password: String by remember { mutableStateOf("") }
    val onPasswordChange = { p: String -> password = p }
    var passwordErrorText: String by remember { mutableStateOf("") }

    var passwordError: Boolean by remember { mutableStateOf(false) }
    val onPasswordErrorChange = { e: Boolean, msn: String ->
        passwordError = e
        passwordErrorText = msn
    }

    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val onPasswordVisibilityChange = { v: Boolean -> passwordVisibility = v }

    Column(
        modifier = Modifier
            .padding(padding)
            .background(color = GrayFCFCFC)
    ) {
        IconButton(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                tint = Blue1A1E25,
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = stringResource(id = R.string.back)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.lets_explore_together),
            style = h1_24_bold_fs.copy(color = Blue1A1E25)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.create_your_account_placoo),
            style = p_16_normal_fs.copy(
                color = Gray7D7F88,
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.email),
            style = p_16_normal_fs.copy(
                color = Blue1A1E25,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldEmail(
            email = email,
            onEmailChange = onEmailChange,
            onEmailErrorChange = onEmailErrorChange,
            emailError = emailError,
            emailErrorText = emailErrorText
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.password),
            style = p_16_normal_fs.copy(
                color = Blue1A1E25,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldPassword(
            password = password,
            onPasswordChange = onPasswordChange,
            onPasswordErrorChange = onPasswordErrorChange,
            passwordError = passwordError,
            passwordErrorText = passwordErrorText,
            onPasswordVisibilityChange = onPasswordVisibilityChange,
            passwordVisibility = passwordVisibility
        )
        Spacer(modifier = Modifier.height(30.dp))
        GradientFilledButtonLarge(text = stringResource(id = R.string.create_account)) {
            onEmailErrorChange(false, "")
            onPasswordErrorChange(false, "")
            val canContinue: Boolean = verifyFields(
                email = email,
                password = password,
                origin = Origin.REGISTER,
                onEmailError = { state: Boolean, msn: String ->
                    onEmailErrorChange(state, msn)
                },
                onPasswordError = { state: Boolean, msn: String ->
                    onPasswordErrorChange(state, msn)
                })

            if (canContinue)
                authViewModel?.createUserWithEmailAndPassword(email, password)
        }
        Spacer(modifier = Modifier.height(30.dp))
        OrDivider()
        Spacer(modifier = Modifier.height(30.dp))
        GoogleFilledButton {
            scope.launch {
                authViewModel?.setGoogle(UiState.Loading)
                val signInIntentSender = googleAuthUiClient?.getIntentSender()
                launcher?.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }
    }
    when (register) {
        is UiState.Success -> {
            LoadingPage(isLoading = false)
            navController.navigate(Screen.HomeScreen.rout)
            authViewModel.setRegister(UiState.None)
        }

        is UiState.Failure -> {
            LoadingPage(isLoading = false)
            register.error?.let { error ->
                scope.launch {
                    snackBarHostState
                        .showSnackbar(
                            message = error,
                            duration = SnackbarDuration.Long
                        )
                }
            }
        }

        is UiState.Loading -> LoadingPage(isLoading = true)
        else -> Unit
    }
    when (google) {
        is UiState.Success -> {
            LoadingPage(isLoading = false)
            navController.navigate(Screen.HomeScreen.rout)
            authViewModel.setGoogle(UiState.None)
        }

        is UiState.Failure -> {
            LoadingPage(isLoading = false)
            google.error?.let { error ->
                scope.launch {
                    snackBarHostState
                        .showSnackbar(
                            message = error,
                            duration = SnackbarDuration.Short
                        )
                }
            }
        }

        is UiState.Loading -> LoadingPage(isLoading = true)
        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterScreen(
        googleAuthUiClient = null,
        launcher = null,
        authViewModel = null
    )
}