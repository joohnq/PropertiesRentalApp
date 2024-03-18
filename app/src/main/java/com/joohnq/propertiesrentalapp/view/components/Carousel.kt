package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.model.entities.PropertyItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePropertiesCarousel(items: List<PropertyItem>) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    HorizontalPager(
        state = pagerState,
        pageSpacing = 10.dp,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
    ) { page ->
        val item: PropertyItem = items[page]
        Box(modifier = Modifier.fillMaxWidth(0.95f)){
            with(item){
                val title = suggestedTexts.title
                val location = suggestedTexts.subtitle
                val image = thumbnail
                val size = size
                val rooms = rooms
                val price = price.toString()
                val operation = operation
                PropertyItemComplete(
                    image = image,
                    title = title,
                    location = location,
                    size = size,
                    rooms = rooms,
                    price = price,
                    operation = operation
                )
            }
        }
    }
}
