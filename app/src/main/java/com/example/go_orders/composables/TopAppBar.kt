package com.example.go_orders.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.R
import com.example.go_orders.ui.theme.Beiruti

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF292524))
            .shadow(0.5f.dp)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF0C0A09))
                    .border(
                        width = 0.8f.dp,
                        color = Color(0xFF292524),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0x44f5f5f4))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.width(70.dp))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0x44f5f5f4)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                text = "اختر مدينتك",
                color = Color.White,
                fontFamily = Beiruti,
                fontSize = 22.sp
            )
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.White
            )
        }
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.go_logo),
            contentDescription = "logo"
        )

    }
}

@Preview
@Composable
fun PreviewTopAppBar() {
    TopAppBar()
}