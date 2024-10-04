package com.example.go_orders.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.R
import com.example.go_orders.composables.TopAppBar
import com.example.go_orders.ui.theme.Beiruti

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Color(0xFF292524))
    ) {
        TopAppBar()
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
//                .weight(1f)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(6.dp)),
                painter = painterResource(R.drawable.hero),
                contentDescription = "Hero pic",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.padding(top = 52.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "👌 Order أطلب يوصلك اسرع",
                        fontFamily = Beiruti,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "كل Go Orders مع",
                            fontFamily = Beiruti,
                            fontSize = 52.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "كل طلباتك في مكان واحد",
                            fontFamily = Beiruti,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0x887C2D12))
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = "اختر مدينتك",
                                fontFamily = Beiruti,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                modifier = Modifier.padding(8.dp),
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location"
                            )
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0x99292524))
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 12.dp),
                                text = "تصفح المطاعم",
                                fontFamily = Beiruti,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                modifier = Modifier.padding(
                                    top = 6.dp,
                                    start = 8.dp,
                                    end = 12.dp,
                                    bottom = 6.dp
                                ),
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}