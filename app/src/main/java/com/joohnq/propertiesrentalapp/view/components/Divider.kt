package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.GrayEBE8F6
import com.joohnq.propertiesrentalapp.view.theme.Purple9E91DA
import com.joohnq.propertiesrentalapp.view.theme.PurpleF3F0FF

@Composable
fun OrDivider() {
    Box(contentAlignment = Alignment.Center) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = PurpleF3F0FF,
        )
        Text(
            text = stringResource(id = R.string.or),
            style = p_12_semibold_inter.copy(color = Purple9E91DA),
            modifier = Modifier
                .background(
                    color = PurpleF3F0FF, shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DividerPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OrDivider()
    }
}