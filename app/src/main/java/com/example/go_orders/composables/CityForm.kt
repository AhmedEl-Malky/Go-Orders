package com.example.go_orders.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.go_orders.state.HomeScreenUIState.CityUIState
import com.example.go_orders.ui.theme.Beiruti
import com.example.go_orders.ui.theme.GoOrdersTheme

@Composable
fun CityForm(
    dismissCityForm: () -> Unit,
    availableCities:List<CityUIState>,
    currentCity:CityUIState,
    onSelectCity: (CityUIState) -> Unit,
    isCitiesMenuExpanded: Boolean,
    expandCitiesMenu: (Boolean) -> Unit
) {
    CityFormContent(
        dismissCityForm = dismissCityForm,
        availableCities = availableCities,
        currentCity = currentCity,
        onSelectCity = onSelectCity,
        isCitiesMenuExpanded = isCitiesMenuExpanded,
        expandCitiesMenu = expandCitiesMenu,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityFormContent(
    dismissCityForm: () -> Unit,
    availableCities:List<CityUIState>,
    currentCity:CityUIState,
    onSelectCity:(CityUIState)->Unit,
    isCitiesMenuExpanded: Boolean,
    expandCitiesMenu: (Boolean) -> Unit,
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
                    expanded = isCitiesMenuExpanded,
                    onExpandedChange = { expandCitiesMenu(isCitiesMenuExpanded) }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = currentCity.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = if (isCitiesMenuExpanded)
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
                        expanded = isCitiesMenuExpanded,
                        onDismissRequest = { expandCitiesMenu(isCitiesMenuExpanded) }
                    ) {
                        availableCities.forEach { city ->
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
                                    expandCitiesMenu(isCitiesMenuExpanded)
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
            dismissCityForm = {},
            availableCities = listOf(),
            currentCity = CityUIState(name = "السادات"),
            onSelectCity = {},
            isCitiesMenuExpanded = false,
            expandCitiesMenu = {}
        )
    }
}