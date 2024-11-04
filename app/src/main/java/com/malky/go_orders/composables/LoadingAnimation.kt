package com.malky.go_orders.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.malky.go_orders.R
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun LoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    LottieAnimation(
        modifier = Modifier.size(220.dp),
        composition = composition,
        iterations = Int.MAX_VALUE,
        speed = 1.25f
    )
}

@Preview(
    locale = "ar",
)
@Composable
fun PreviewLoadingAnimation() {
    GoOrdersTheme {
        LoadingAnimation()
    }
}