package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CustomImage(path: String, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(path ?: "https://images.pexels.com/photos/106399/pexels-photo-106399.jpeg")
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}