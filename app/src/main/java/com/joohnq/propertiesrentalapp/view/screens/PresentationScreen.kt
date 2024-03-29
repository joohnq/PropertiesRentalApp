package com.joohnq.propertiesrentalapp.view.screens

import UiState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.Screen
import com.joohnq.propertiesrentalapp.view.components.CustomSnackBarHost
import com.joohnq.propertiesrentalapp.view.components.GradientFilledButtonLarge
import com.joohnq.propertiesrentalapp.view.components.OutlineButtonLarge
import com.joohnq.propertiesrentalapp.view.components.h1_24_bold_fs
import com.joohnq.propertiesrentalapp.view.components.p_16_normal_fs
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.GradientWhiteToTransparent
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC
import com.joohnq.propertiesrentalapp.viewmodel.AuthViewModel

@Composable
fun PresentationScreen(
    padding: PaddingValues = PaddingValues(16.dp),
    authViewModel: AuthViewModel?,
    navController: NavController,
) {
    authViewModel?.apply {
        val state = UiState.None
        setGoogle(state)
        setLogin(state)
        setRegister(state)
    }

    Column(
        modifier = Modifier
            .padding(padding)
            .background(color = GrayFCFCFC)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(color = GrayFCFCFC)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .then(
                        Modifier.graphicsLayer(
                            scaleX = 1.5f,
                            scaleY = 1.5f,
                            translationY = 0.1f
                        )
                    ),
                painter = painterResource(id = R.drawable.gallery),
                contentDescription = "Gallery Illustration",
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(
                            brush = GradientWhiteToTransparent,
                            size = size
                        )
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .weight(1f)
                .background(color = GrayFCFCFC),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.new_place_new_home),
                style = h1_24_bold_fs.copy(color = Blue1A1E25, textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.are_you_ready_to_uproot_start_over_in_a_new_area),
                style = p_16_normal_fs.copy(
                    color = Gray7D7F88,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                GradientFilledButtonLarge(text = stringResource(id = R.string.login)) {
                    navController.navigate(Screen.LoginScreen.rout)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            OutlineButtonLarge(text = stringResource(id = R.string.signup)) {
                navController.navigate(Screen.RegisterScreen.rout)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresentationPreview() {
    val navController = rememberNavController()
    PresentationScreen(authViewModel = null, navController = navController)
}