package com.malky.go_orders.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.go_orders.R
import com.malky.go_orders.composables.OrdersLazyColumn
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun CartScreen(){
    CartScreenContent()
}

@Composable
private fun CartScreenContent() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "سلة المشتريات",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "(0ج)",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "⚠️ تنويه: يجب أن تكون عناصر الطلب من نفس المطعم",
                fontFamily = Beiruti,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(R.drawable.empty_cart),
            contentDescription = "Empty Cart"
        )
//            OrdersLazyColumn()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1080px,height=2400px,dpi=441",
    locale = "ar"
)
@Composable
private fun PreviewCartScreen() {
    GoOrdersTheme {
        CartScreen()
    }
}