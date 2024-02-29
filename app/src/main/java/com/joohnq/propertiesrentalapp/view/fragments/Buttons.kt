package com.joohnq.propertiesrentalapp.view.fragments

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.propertiesrentalapp.view.theme.Purple62
import com.joohnq.propertiesrentalapp.view.theme.Purple91

@Composable
fun GradientFilledButton(
    modifier: Modifier,
    text: String,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.Medium,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit
) {
    val gradientColors = listOf(
        Purple91,
        Purple62
    )
    val cornerRadius = 30.dp

    return Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = fontSize.sp,
                color = Color.White,
                fontWeight = fontWeight
            )
        }
    }
}

@Composable
fun GradientFilledButtonLarge(text: String, onClick: () -> Unit) {

    return GradientFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
            .height(50.dp),
        text = text,
        fontSize = 16,
        roundedCornerShape = RoundedCornerShape(16.dp),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GradientFilledButtonLarge(text = "Log in") {
        println("Foi")
    }
}