package com.malky.go_orders.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.malky.go_orders.state.HomeUIState
import com.malky.go_orders.state.HomeUIState.CityUIState
import com.malky.go_orders.ui.theme.Beiruti
import com.malky.go_orders.ui.theme.GoOrdersTheme

@Composable
fun CityForm(
    state: HomeUIState,
    dismissCityForm: () -> Unit,
    onSelectCity: (CityUIState) -> Unit,
    expandCitiesMenu: () -> Unit,
    collapseCitiesMenu: () -> Unit
) {
    CityFormContent(
        state = state,
        dismissCityForm = dismissCityForm,
        onSelectCity = onSelectCity,
        expandCitiesMenu = expandCitiesMenu,
        collapseCitiesMenu = collapseCitiesMenu
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityFormContent(
    state: HomeUIState,
    dismissCityForm: () -> Unit,
    onSelectCity: (CityUIState) -> Unit,
    expandCitiesMenu: () -> Unit,
    collapseCitiesMenu: () -> Unit
) {
    Dialog(
        onDismissRequest = { dismissCityForm() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(6.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "اختر مدينتك",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "برجاء اختيار مدينتك أو منطقتك حتي تتمكن من تصفح المطاعم والمحلات المتواجدة بهذه المنطقة.",
                    fontFamily = Beiruti,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = state.isCitiesMenuExpanded,
                    onExpandedChange = { expandCitiesMenu() }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = state.city.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = if (state.isCitiesMenuExpanded)
                                    Icons.Default.KeyboardArrowUp
                                else
                                    Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = state.isCitiesMenuExpanded,
                        onDismissRequest = { collapseCitiesMenu() }
                    ) {
                        state.availableCities.toData()?.forEach { city ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = city.name,
                                        textAlign = TextAlign.Right
                                    )
                                },
                                onClick = {
                                    onSelectCity(city)
                                    collapseCitiesMenu()
                                    dismissCityForm()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(locale = "ar")
@Composable
fun PreviewCityForm() {
    GoOrdersTheme {
        CityForm(
            state = HomeUIState(),
            dismissCityForm = {},
            onSelectCity = {},
            expandCitiesMenu = {},
            collapseCitiesMenu = {}
        )
    }
}