package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.model.entities.Section
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.Gray434343
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.White

@Composable
fun PropertyItemComplete(
    image: String,
    title: String,
    location: String,
    size: String,
    rooms: Int,
    price: String,
    section: String
) {
    val shape = RoundedCornerShape(10.dp)
    Card(
        shape = shape,
        modifier = Modifier
            .heightIn(min = 180.dp, max = 190.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Gray434343,
                shape = shape,
                spotColor = Gray434343
            ),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
            ) {
                CustomImage(path = image, stringResource(id = R.string.property_image))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(10.dp)
            ) {
                Text(text = title, style = p_16_normal_fs.copy(color = Blue1A1E25), maxLines = 2)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = location, style = p_13_normal_fs.copy(color = Gray7D7F88), maxLines = 2)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bed),
                            contentDescription = stringResource(id = R.string.filter),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = stringResource(id = R.string.rooms, rooms.toString()),
                            style = p_13_normal_fs.copy(color = Gray7D7F88)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_size),
                            contentDescription = stringResource(id = R.string.filter),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = size,
                            style = p_13_normal_fs.copy(color = Gray7D7F88)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = stringResource(id = R.string.price) + price,
                        style = p_18_bold_inter.copy(color = Blue1A1E25)
                    )
                    if (section == Section.TO_RENT) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(id = R.string.por_month),
                            style = p_12_medium_inter.copy(color = Gray7D7F88)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertyItemCompletePreview() {
    PropertyItemComplete("Title", "Location", "", "123.00", 123, "123", Section.TO_RENT)
}