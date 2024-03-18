package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.view.theme.GrayE3E3E7
import com.joohnq.propertiesrentalapp.view.theme.GrayF2F2F3

@Composable
fun RadioGroupTwice(
    itemsList: List<String>,
    initialSelectedIndex: Int,
    onChange: (Int) -> Unit
) {
    val cornerRadius = 16.dp
    var selectedIndex by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GrayF2F2F3, shape = RoundedCornerShape(30.dp))
            .border(color = GrayE3E3E7, width = 0.8.dp, shape = RoundedCornerShape(30.dp))
            .padding(8.dp)
            .height(48.dp)
    ) {
        itemsList.forEachIndexed { index, item ->
            RadioButtonLarge(
                selected = selectedIndex == index,
                text = item,
                modifier = Modifier.weight(1f)
            ) {
                selectedIndex = index
                onChange(index)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioGroupRentOrBuyPreview() {
    RadioGroupTwice(listOf("I need to rent", "I need to buy"), 0) {

    }
}