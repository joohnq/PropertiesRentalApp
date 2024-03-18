package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.joohnq.propertiesrentalapp.view.theme.GradientGrayF2F2F3
import com.joohnq.propertiesrentalapp.view.theme.GradientPurpleToPurple
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.White

@Composable
fun CustomRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(
        color = Color.White
    ),
    selected: Boolean,
    cornerRadius: Dp,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier =
        modifier
            .offset(0.dp, 0.dp)
            .fillMaxHeight()
            .zIndex(if (selected) 1f else 0f)
            .background(
                brush = if (selected) GradientPurpleToPurple else GradientGrayF2F2F3,
                shape = RoundedCornerShape(cornerRadius)
            ),
        colors = if (selected) {
            ButtonDefaults.outlinedButtonColors(
                contentColor = White
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                contentColor = Gray7D7F88
            )
        }
    ) {
        Text(text, style = textStyle)
    }
}

@Composable
fun RadioButtonLarge(
    selected: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CustomRadioButton(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        text = text,
        cornerRadius = 30.dp,
        textStyle = p_15_medium_fs
    )
}

@Preview(showBackground = true)
@Composable
fun CustomRadioButtonPreview() {
    RadioButtonLarge(true, "", onClick = {})
}