package com.example.go_orders.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.go_orders.R
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.Category
import com.example.go_orders.ui.theme.Beiruti

@Composable
fun CategoryCard(
    category: Category
) {
    CategoryCardContent(category)
}


@Composable
private fun CategoryCardContent(
    category: Category
) {
    Card(
        modifier = Modifier
            .size(90.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x44f5f5f4)),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(category.img),
                contentDescription = category.name
            )
            Text(
                text = category.name,
                color = Color.White,
                fontFamily = Beiruti,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }

}


@Preview
@Composable
private fun PreviewCategoryCard() {
    CategoryCard(Category(name = "الكل", img = R.drawable.all, isSelected = true))
}
