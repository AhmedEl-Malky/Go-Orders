package com.example.goorders.core.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.composables.icons.lucide.ChevronDown
import com.composables.icons.lucide.Lucide
import com.example.goorders.core.presentation.theme.Beiruti
import com.example.goorders.core.presentation.theme.GoOrdersTheme
import com.example.goorders.mainscreen.presentation.MainActions
import com.example.goorders.mainscreen.presentation.MainScreenState


@Composable
fun CityForm(
    state: MainScreenState,
    onAction: (MainActions) -> Unit
) {
    CityFormContent(
        state = state,
        onAction = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityFormContent(
    state: MainScreenState,
    onAction: (MainActions) -> Unit
) {
    val animateRotation by animateFloatAsState(
        targetValue = if (state.isCitiesMenuExpanded) 180f else 0f,
    )
    Dialog(
        onDismissRequest = {
            onAction(MainActions.OnCityFormToggle)
        },
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
                Box {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        onClick = {
                            onAction(MainActions.OnCitiesMenuToggle)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            width = 0.8f.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = state.currentCity?.name ?: "المدينة",
                                fontFamily = Beiruti,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Icon(
                                modifier = Modifier
                                    .size(16.dp)
                                    .rotate(animateRotation),
                                tint = MaterialTheme.colorScheme.onBackground,
                                imageVector = Lucide.ChevronDown,
                                contentDescription = "Cities Menu",
                            )
                        }
                    }
                    DropdownMenu(
                        modifier = Modifier
                            .fillMaxWidth(0.92f)
                            .heightIn(max = 280.dp)
                            .background(MaterialTheme.colorScheme.background),
                        expanded = state.isCitiesMenuExpanded,
                        onDismissRequest = {
                            onAction(MainActions.OnCitiesMenuToggle)
                        },
                        offset = DpOffset(x = 0.dp, y = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            width = 0.8f.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    ) {
                        state.availableCities.forEach { city ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = city.name,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = Beiruti
                                    )
                                },
                                onClick = {
                                    onAction(MainActions.OnCitySelect(city))
                                    onAction(MainActions.OnCitiesMenuToggle)
                                    onAction(MainActions.OnCityFormToggle)
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
            state = MainScreenState(),
            onAction = {}
        )
    }
}