package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.propertiesrentalapp.view.theme.Gray47
import com.joohnq.propertiesrentalapp.view.theme.GrayE3
import com.joohnq.propertiesrentalapp.view.theme.Purple62
import com.joohnq.propertiesrentalapp.view.theme.Purple91
import com.joohnq.propertiesrentalapp.view.theme.sfProDisplayFamily

@Composable
fun GradientFilledButton(
    modifier: Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Medium,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp),
    onClick: () -> Unit
) {
    val gradientColors = listOf(
        Purple91,
        Purple62
    )

    return Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = roundedCornerShape
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
                style = p_16_medium_fs.copy(color = Color.White),
            )
        }
    }
}

@Composable
fun OutlineButton(
    modifier: Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Medium,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp),
    onClick: () -> Unit
) {
    return Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = roundedCornerShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = roundedCornerShape,
                )
                .clip(roundedCornerShape)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = p_16_medium_fs.copy(color = Gray47),
            )
        }
    }
}

@Composable
fun GradientFilledButtonLarge(text: String, onClick: () -> Unit) {
    val roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp)

    return GradientFilledButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
            .height(50.dp),
        text = text,
        onClick = onClick
    )
}

@Composable
fun OutlineButtonLarge(text: String, onClick: () -> Unit) {
    val roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp)

    return OutlineButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)
            .height(50.dp)
            .border(width = 1.dp, color = GrayE3, shape = roundedCornerShape),
        text = text,
        onClick = onClick
    )
}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column {
        GradientFilledButtonLarge(text = "Log in") {
            println("Foi")
        }
        OutlineButtonLarge(text = "Log in") {
            println("Foi")
        }
    }
}