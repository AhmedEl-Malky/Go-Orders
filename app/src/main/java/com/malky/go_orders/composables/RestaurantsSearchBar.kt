package com.malky.go_orders.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.go_orders.screens.events.ExploreRestaurantsEvents
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun RestaurantsSearchBar(
    isOpenFilter: Boolean,
    searchInput: String,
    onEvent:(ExploreRestaurantsEvents) -> Unit
) {
    RestaurantsSearchBarContent(
        isOpenFilter,
        searchInput,
        onEvent = onEvent
    )
}

@Composable
fun RestaurantsSearchBarContent(
    isOpenFilter: Boolean,
    searchInput: String,
    onEvent: (ExploreRestaurantsEvents) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(5f)
                .height(52.dp)
                .padding(top = 0.dp, bottom = 0.dp, end = 0.dp, start = 4.dp),
            value = searchInput,
            onValueChange = { onEvent(ExploreRestaurantsEvents.SearchForRestaurant(it)) },
            textStyle = TextStyle(
                fontFamily = Beiruti,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
            ),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "البحث عن مطاعم ...",
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            )
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "مفتوح الآن",
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Beiruti,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
            Switch(
                checked = isOpenFilter,
                onCheckedChange = {onEvent(ExploreRestaurantsEvents.FilterOpenedRestaurants(it))},
                colors = SwitchDefaults.colors(
                    uncheckedTrackColor = MaterialTheme.colorScheme.onTertiary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    checkedThumbColor = MaterialTheme.colorScheme.background,
                    uncheckedThumbColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Preview(locale = "ar")
@Composable
fun PreviewRestaurantsSearchBar() {
    GoOrdersTheme {
        RestaurantsSearchBar(
            false,
            "",
            onEvent = {}
        )
    }
}