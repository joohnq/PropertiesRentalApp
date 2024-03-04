package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.theme.Blue222831
import com.joohnq.propertiesrentalapp.view.theme.Blue475569
import com.joohnq.propertiesrentalapp.view.theme.GradientPurpleToPurple
import com.joohnq.propertiesrentalapp.view.theme.Gray475569
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.GrayE2E8F0
import com.joohnq.propertiesrentalapp.view.theme.GrayE3E3E7

@Composable
fun GradientFilledButton(
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
                    brush = Brush.linearGradient(colors = GradientPurpleToPurple),
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
fun AppleFilledButton(
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp),
    onClick: () -> Unit
) {
    return Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Blue222831,
                shape = roundedCornerShape
            )
            .clip(roundedCornerShape)
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = Color.White,
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = "Sign in with Apple"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign in with Apple",
                style = p_16_medium_inter.copy(color = Color.White, textAlign = TextAlign.Center),
            )
        }
    }
}

@Composable
fun GoogleFilledButton(
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp),
    onClick: () -> Unit
) {
    return Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = roundedCornerShape
            )
            .border(width = 1.dp, color = GrayE2E8F0, shape = roundedCornerShape)
            .clip(roundedCornerShape)
            .height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Sign in with Google",
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign in with Google",
                style = p_16_medium_inter.copy(color = Blue475569, textAlign = TextAlign.Center),
            )
        }
    }
}

@Composable
fun ForgotPasswordButton(
    onClick: () -> Unit
) {
    return Button(
        modifier = Modifier
            .height(35.dp)
            .background(color = Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = onClick,
    ) {
        Text(
            text = "Forgot password?",
            style = p_14_normal_fs.copy(color = Gray7D7F88, textAlign = TextAlign.Center),
        )
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
                .fillMaxWidth(1f)
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
                style = p_16_medium_fs.copy(color = Gray475569),
            )
        }
    }
}

@Composable
fun GradientFilledButtonLarge(text: String, onClick: () -> Unit) {
    val roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp)

    return GradientFilledButton(
        modifier = Modifier
            .fillMaxWidth(1f)
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
            .height(50.dp)
            .border(width = 1.dp, color = GrayE3E3E7, shape = roundedCornerShape),
        text = text,
        onClick = onClick
    )
}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GradientFilledButtonLarge(text = "Log in") {
            println("Foi")
        }
        OutlineButtonLarge(text = "Log in") {
            println("Foi")
        }

        AppleFilledButton {
            println("Foi")
        }

        GoogleFilledButton {
            println("Foi")
        }

        ForgotPasswordButton {
            println("Foi")
        }
    }
}