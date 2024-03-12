package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.view.theme.Blue222831

@Composable
fun CustomSnackBarHost(snackBarHostState: SnackbarHostState) {
    SnackbarHost(
        modifier = Modifier.background(color = Blue222831, shape = RoundedCornerShape(5.dp)),
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar{
                Text(
                    text = data.visuals.message,
                    style = p_14_normal_inter.copy(color = Color.White)
                )
            }
        })
}
