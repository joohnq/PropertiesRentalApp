package com.joohnq.propertiesrentalapp.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.components.GradientFilledButtonLarge
import com.joohnq.propertiesrentalapp.view.components.OutlineButtonLarge
import com.joohnq.propertiesrentalapp.view.components.h1_24_bold_fs
import com.joohnq.propertiesrentalapp.view.components.p_16_normal_fs
import com.joohnq.propertiesrentalapp.view.theme.Blue1A
import com.joohnq.propertiesrentalapp.view.theme.Gray7D
import com.joohnq.propertiesrentalapp.view.theme.PropertiesRentalAppTheme

class OnboardingScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertiesRentalAppTheme {
                OnboardingScreen()
            }
        }
    }
}

@Composable
fun OnboardingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(it)) {
                val gradientWhiteToTransparent = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.7f to Color.Transparent,
                        1f to Color.White,
                    ),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(.7f)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)),
                        painter = painterResource(id = R.drawable.gallery),
                        contentDescription = "Gallery Illustration"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                drawRect(
                                    brush = gradientWhiteToTransparent,
                                    size = size
                                )
                            }
                    )
                }
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "New Place, New Home!",
                        style = h1_24_bold_fs.copy(color = Blue1A, textAlign = TextAlign.Center)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Are you ready to uproot and start over in a new area? Placoo will help you on your journey!",
                        style = p_16_normal_fs.copy(
                            color = Gray7D,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    GradientFilledButtonLarge(text = "Log in") {
                        println("Foi")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlineButtonLarge(text = "Sign up") {
                        println("Foi")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OnboardingScreen()
}