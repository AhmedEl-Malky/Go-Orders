package com.example.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.go_orders.R

@Composable
fun ErrorAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_animation))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(280.dp),
            composition = composition,
            iterations = Int.MAX_VALUE
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = "spec:width=1080px,height=2400px,dpi=441",
    locale = "ar",
)
@Composable
fun PreviewErrorAnimation(){
    ErrorAnimation()
}