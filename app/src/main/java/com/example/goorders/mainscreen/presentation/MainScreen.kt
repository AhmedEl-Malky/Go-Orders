package com.example.goorders.mainscreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPinned
import com.composables.icons.lucide.Utensils
import com.example.goorders.R
import com.example.goorders.core.presentation.components.CityForm
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.core.presentation.components.TopAppBar


@Composable
fun MainScreen(
    state: MainScreenState,
    navigateToHome: () -> Unit,
    navigateToCart:() -> Unit,
    onAction: (MainActions) -> Unit
) {
    MainScreenContent(
        state = state,
        navigateToHome = navigateToHome,
        navigateToCart = navigateToCart,
        onAction = onAction
    )
//    when (state.availableCities) {
//        is state.Loading ->
//            AnimatedVisibility(true) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    LoadingAnimation()
//                }
//            }
//
//
//        is State.Success ->
//            AnimatedVisibility(true) {
//                HomeScreenContent(
//                    navController = navController,
//                    state = state,
//                    onAction = onAction
//                )
//            }
//
//
//        is State.Error ->
//            AnimatedVisibility(true) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(24.dp),
//                        painter = painterResource(R.drawable.error),
//                        contentDescription = "Error"
//                    )
//                }
//            }
//    }
}

@Composable
private fun MainScreenContent(
    state: MainScreenState,
    onAction: (MainActions) -> Unit,
    navigateToHome:() -> Unit,
    navigateToCart:() -> Unit,
) {
    if (state.isCityFormVisible) {
        CityForm(
            state = state,
            onAction = onAction
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                state = state,
                navigateToCart = navigateToCart,
                onAction = onAction
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp)),
                    painter = painterResource(R.drawable.hero),
                    contentDescription = "Hero pic",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier.padding(top = 100.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "أطلب يوصلك أسرع Order 👌",
                            fontFamily = Beiruti,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.background
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "مع Go Orders كل",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "طلباتك في مكان واحد",
                                fontFamily = Beiruti,
                                fontSize = MaterialTheme.typography.displayMedium.fontSize,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Column(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = {
                                    onAction(MainActions.OnCityFormToggle)
                                },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                contentPadding = PaddingValues(
                                    start = 12.dp,
                                    end = 16.dp,
                                    top = 12.dp,
                                    bottom = 12.dp
                                )
                            ) {
                                Icon(
                                    imageVector = Lucide.MapPinned,
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "اختر مدينتك",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Button(
                                contentPadding = PaddingValues(
                                    start = 16.dp,
                                    end = 12.dp,
                                    top = 12.dp,
                                    bottom = 12.dp
                                ),
                                onClick = {
                                    navigateToHome()
                                },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(end = 12.dp),
                                    text = "تصفح المطاعم",
                                    fontFamily = Beiruti,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Lucide.Utensils,
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.primary
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=1080px,height=2400px,dpi=440",
    locale = "ar"
)
@Composable
private fun PreviewHomeScreen() {
    GoOrdersTheme {
        MainScreen(
            state = MainScreenState(),
            onAction = {},
            navigateToHome = {},
            navigateToCart = {}
        )
    }
}