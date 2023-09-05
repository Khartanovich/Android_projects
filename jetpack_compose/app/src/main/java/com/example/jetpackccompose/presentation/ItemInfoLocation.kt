package com.example.jetpackccompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.jetpackccompose.R
import com.example.jetpackccompose.entity.LocationEntity

@Composable
fun ItemInfoLocation(infoLocation: LocationEntity) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_not_listed_location_24),
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = infoLocation.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Row {
                    Text(
                        text = "type:",
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                    Text(
                        text = infoLocation.type,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
                Row {
                    Text(
                        text = "dimension:",
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                    Text(
                        text = infoLocation.dimension,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
                Row {
                    Text(
                        text = "residents",
                        color = Color.White
                    )
                    IconButton(
                        modifier = Modifier.padding(start = 6.dp),
                        onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = if (expanded) {
                                stringResource(R.string.show_less)
                            } else {
                                stringResource(R.string.show_more)
                            },
                            tint = Color.White
                        )
                    }
                }
                if (expanded) {
                    for (resident in infoLocation.residents) {
                        Text(
                            text = resident,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}