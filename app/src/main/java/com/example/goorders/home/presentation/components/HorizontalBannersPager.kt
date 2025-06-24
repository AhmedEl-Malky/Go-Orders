package com.example.goorders.home.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.BorderLight
import com.example.goorders.core.presentation.theme.CardDark
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.core.presentation.theme.PrimaryLight
import com.example.goorders.home.domain.Banner
import kotlinx.coroutines.delay

@Composable
fun HorizontalBannersPager(
    banners: List<Banner>
) {
    val state = rememberPagerState { banners.size }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            state.animateScrollToPage(
                page = (state.currentPage + 1) % (state.pageCount),
                animationSpec = tween(durationMillis = 900)
            )
        }
    }
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(vertical = 6.dp),
        state = state,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        reverseLayout = true
    ) { page->
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = banners[page].img,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardDark.copy(alpha = 0.7f))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 24.dp, horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = banners[page].title,
                        fontFamily = Beiruti,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = banners[page].subtitle,
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = banners[page].highlight,
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        ),
                        border = BorderStroke(
                            width = 1.5f.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = banners[page].buttonText,
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

    }
}


@Preview(locale = "ar")
@Composable
fun PreviewHorizontalBannersPager() {
    GoOrdersTheme {
        HorizontalBannersPager(emptyList())
    }
}