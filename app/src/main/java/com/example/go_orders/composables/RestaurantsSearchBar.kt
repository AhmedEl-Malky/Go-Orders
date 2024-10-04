package com.example.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.ui.theme.Beiruti

@Composable
fun RestaurantsSearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0x77292524)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "مفتوح الآن",
                color = Color.White,
                fontFamily = Beiruti,
                fontSize = 14.sp,
            )
            Switch(
                checked = false,
                onCheckedChange = {},
                colors = SwitchDefaults.colors(
                    uncheckedTrackColor = Color(0xFFA8A29E),
                    checkedTrackColor = Color(0xFFEA580C),
                    checkedThumbColor = Color.Black,
                    uncheckedThumbColor = Color.Black
                )
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .weight(5f)
                .height(52.dp)
                .padding(end = 4.dp),
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
            ),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = " ... البحث عن مطعم ",
                    color = Color.White,
                    textAlign = TextAlign.End,
                    fontSize = 12.sp
                )
            }
        )
    }
}

@Preview
@Composable
fun PreviewRestaurantsSearchBar() {
    RestaurantsSearchBar()
}