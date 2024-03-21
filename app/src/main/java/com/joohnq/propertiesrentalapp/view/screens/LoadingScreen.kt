package com.joohnq.propertiesrentalapp.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.joohnq.propertiesrentalapp.view.components.LoadingPage
import com.joohnq.propertiesrentalapp.view.theme.PropertiesRentalAppTheme

@Composable
fun LoadingScreen() {
    LoadingPage(isLoading = true)
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingPage(isLoading = true)
}