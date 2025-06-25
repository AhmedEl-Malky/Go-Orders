package com.example.goorders.restaurant.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.restaurant.domain.MenuImage

@Composable
fun MenuImagesPager(
    menuImages:List<MenuImage>
) {
    val pagerState = rememberPagerState { menuImages.size }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f))
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = "صور المنيو",
            fontFamily = Beiruti,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            pageSpacing = 8.dp,
            reverseLayout = true,
        ) { page ->
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp)),
                model = menuImages[page].url,
                contentDescription = "Menu"
            )
        }
    }
}

@Preview(locale = "ar")
@Composable
private fun PreviewMenuImagesPager() {
    GoOrdersTheme {
        MenuImagesPager(
            menuImages = emptyList()
        )
    }
}