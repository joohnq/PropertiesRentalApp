package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.compose_loading.DoubleBounce
import com.joohnq.propertiesrentalapp.view.theme.Purple6246EA
import com.joohnq.propertiesrentalapp.view.theme.PurpleF1F1FE
import com.joohnq.propertiesrentalapp.view.theme.SemiWhite50

@Composable
fun LoadingPage(isLoading: Boolean) {
    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = SemiWhite50)
                .pointerInput(Unit) {},
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DoubleBounce(size = DpSize(50.dp, 50.dp), color = Purple6246EA)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPagePreview() {
    LoadingPage(true)
}