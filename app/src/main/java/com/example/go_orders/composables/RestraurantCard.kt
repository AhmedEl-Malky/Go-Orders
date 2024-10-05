package com.example.go_orders.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.R
import com.example.go_orders.ui.theme.Beiruti

@Composable
fun RestaurantCard(
) {
    RestaurantCardContent()
}

@Composable
private fun RestaurantCardContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
//                modifier = Modifier.weight(3.5f),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)),
                    painter = painterResource(R.drawable.rocket),
                    contentDescription = "Restaurant banner",
                )
                Image(
                    modifier = Modifier
                        .padding(end = 12.dp, bottom = 12.dp)
                        .size(72.dp)
                        .clip(RoundedCornerShape(topStart = 18.dp)),
                    painter = painterResource(R.drawable.rocket_logo),
                    contentDescription = "Restaurant logo"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 6.dp, bottomStart = 6.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                            .background(MaterialTheme.colorScheme.surfaceContainerLow)
                            .padding(
                                vertical = 2.dp,
                                horizontal = 8.dp
                            ),

                        text = "مفتوح الآن",
                        fontSize = 14.sp,
                        fontFamily = Beiruti,
                        color = MaterialTheme.colorScheme.surfaceContainerHigh
                        )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Restaurant Name",
                        fontFamily = Beiruti,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.End
                    )
                    Image(
                        modifier = Modifier.size(22.dp).padding(start = 4.dp),
                        painter = painterResource(R.drawable.verified),
                        contentDescription = "Verified"
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Description",
                    fontFamily = Beiruti,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.End,
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 6.dp),
                    onClick = {},
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRestaurantCard() {
    RestaurantCard()
}