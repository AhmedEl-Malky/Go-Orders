package com.example.go_orders.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.go_orders.R
import com.example.go_orders.state.HomeScreenUIState.CityUIState
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun TopAppBar(
    city: CityUIState,
    showCityForm: () -> Unit,
    dismissCityForm: () -> Unit,
    isCityFormShown: Boolean,
    availableCities: List<CityUIState>,
    onSelectCity: (CityUIState) -> Unit,
    isCitiesMenuExpanded: Boolean,
    expandCitiesMenu: (Boolean) -> Unit,
) {
    TopAppBarContent(
        city = city,
        showCityForm = showCityForm,
        dismissCityForm = dismissCityForm,
        isCityFormShown = isCityFormShown,
        availableCities = availableCities,
        onSelectCity = onSelectCity,
        isCitiesMenuExpanded = isCitiesMenuExpanded,
        expandCitiesMenu = expandCitiesMenu,
    )
}

@Composable
fun TopAppBarContent(
    city: CityUIState,
    showCityForm: () -> Unit,
    dismissCityForm: () -> Unit,
    isCityFormShown: Boolean,
    availableCities: List<CityUIState>,
    onSelectCity: (CityUIState) -> Unit,
    isCitiesMenuExpanded: Boolean,
    expandCitiesMenu: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .shadow(0.1f.dp)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.go_logo),
            contentDescription = "logo"
        )
        Button(
            onClick = { showCityForm() },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(R.drawable.map),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${city.name}",
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = Beiruti,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.width(48.dp))
        Row {
            Button(
                modifier = Modifier
                    .size(48.dp),
                onClick = {},
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
                contentPadding = PaddingValues(10.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.cart),
                    contentDescription = "Cart",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                modifier = Modifier
                    .size(48.dp),
                onClick = {},
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF)),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
                contentPadding = PaddingValues(10.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

        }
    }
    if (isCityFormShown) {
        CityForm(
            dismissCityForm = dismissCityForm,
            availableCities = availableCities,
            onSelectCity = onSelectCity,
            isCitiesMenuExpanded = isCitiesMenuExpanded,
            expandCitiesMenu = expandCitiesMenu,
            currentCity = city
        )
    }
}

@Preview(locale = "ar")
@Composable
fun PreviewTopAppBar() {
    GoOrdersTheme {
        TopAppBar(
            CityUIState(name = "السادات"),
            showCityForm = {},
            dismissCityForm = {},
            isCityFormShown = false,
            availableCities = listOf(),
            onSelectCity = {},
            isCitiesMenuExpanded = false,
            expandCitiesMenu = {},
        )
    }
}