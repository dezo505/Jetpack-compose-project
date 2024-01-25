package com.example.jetpackcomposeproject.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeproject.R
import com.example.jetpackcomposeproject.data.model.Sport

@Composable
fun SportDropdown(selectedSport: Sport?, onSportSelected: (Sport) -> Unit) {
    val sports = Sport.values()

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text("Sport", modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                }
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = selectedSport?.iconResId ?: R.drawable.ic_cross_grey),
                    contentDescription = Sport.FOOTBALL.name,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = selectedSport?.fullName ?: "Select Sport",
                    color = LocalContentColor.current,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                sports.forEach { sport ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .width(IntrinsicSize.Min),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = sport.iconResId),
                                contentDescription = sport.name,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        text = { Text(sport.fullName) },
                        onClick = {
                            onSportSelected(sport)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}