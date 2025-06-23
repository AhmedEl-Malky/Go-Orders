package com.example.goorders.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPinned
import com.composables.icons.lucide.ShoppingCart
import com.composables.icons.lucide.User
import com.example.goorders.R
import com.example.goorders.mainscreen.presentation.MainAction
import com.example.goorders.mainscreen.presentation.MainScreenState
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme

@Composable
fun TopAppBar(
    state: MainScreenState,
    navController: NavController,
    onAction: (MainAction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .shadow(0.1f.dp)
            .padding(horizontal = 12.dp)
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.go_logo),
                contentDescription = "logo"
            )
            Button(
                modifier = Modifier.size(40.dp),
                onClick = {
//                onAction(MainAction.ShowCityForm)
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        imageVector = Lucide.MapPinned,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "state.city.name",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = Beiruti,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier
                    .size(48.dp),
                onClick = {
//                    navController.navigate(Navigation.CartScreen)
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Lucide.ShoppingCart,
                    contentDescription = "Cart",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
            Button(
                modifier = Modifier
                    .size(48.dp),
                onClick = {
//                    navController.navigate(Navigation.AuthenticationScreen)
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Lucide.User,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}

@Preview(
    locale = "ar"
)
@Composable
fun PreviewTopAppBar() {
    GoOrdersTheme {
        TopAppBar(
            state = MainScreenState(),
            navController = NavController(context = LocalContext.current),
            onAction = {}
        )
    }
}