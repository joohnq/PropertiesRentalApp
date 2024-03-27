package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.propertiesrentalapp.model.entities.Result

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePropertiesCarousel(items: List<Result>, section: String) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    HorizontalPager(
        state = pagerState,
    ) { page ->
        val item: Result = items[page]
        Box(modifier = Modifier.fillMaxWidth(0.95f)) {
            item.run {
                val address = location?.address
                val title = address?.line ?: ""
                val location =
                    (address?.state ?: "") + ", " + (address?.country ?: "")
                val image = primaryPhoto?.href ?: photos?.get(0)?.href ?: ""
                val size = description?.sqft.toString() + " sqft"
                val rooms = description?.beds ?: 0
                val price = listPrice.toString() ?: ""

                PropertyItemComplete(
                    image = image,
                    title = title,
                    location = location,
                    size = size,
                    rooms = rooms,
                    price = price,
                    section = section
                )
            }
        }
    }
}
