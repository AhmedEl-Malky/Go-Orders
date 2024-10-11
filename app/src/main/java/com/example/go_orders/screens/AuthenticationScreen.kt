package com.example.go_orders.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.go_orders.composables.GoogleSignInButton
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun AuthenticationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row {
                Text(
                    text = "تسجيل الدخول الي حساب",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = " Go Orders",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = " الخاص بي",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "يرجى تسجيل الدخول حتي تتمكنوا من استكمال طلباتكم",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontFamily = Beiruti,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.onTertiary
            )
            GoogleSignInButton()
        }

    }
}

@Preview(
    showBackground = true, showSystemUi = true, locale = "ar",
    device = "spec:width=1080px,height=2400px,dpi=441"
)
@Composable
fun PreviewAuthenticationScreen() {
    GoOrdersTheme {
        AuthenticationScreen()
    }
}