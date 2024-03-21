package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.propertiesrentalapp.model.entities.Home

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePropertiesCarousel(items: List<Home>, section: String) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    HorizontalPager(
        state = pagerState,
    ) { page ->
        val item: Home = items[page]
        Box(modifier = Modifier.fillMaxWidth(0.95f)) {
            item.homeData?.run {
                val title = addressInfo?.formattedStreetLine + "-" + addressInfo?.location
                val location = addressInfo?.city + ", " + addressInfo?.state
                val image = photosInfo?.posterFrameUrl ?: ""
                val size = lotSize?.amount?.value ?: ""
                val rooms = beds?.value ?: 0
                val price = priceInfo?.amount?.value ?: ""
                println("ITEEEEEMMMM: " + photosInfo.toString())
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
