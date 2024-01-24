package com.example.jetpackcomposeproject.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeproject.R


@Composable
fun PrimaryScreen() {
    val context = LocalContext.current

    val iconNumber by remember {
        derivedStateOf {
            val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            sharedPreferences.getInt("currentImageIndex", -1)
        }
    }

    val drawableId: Int = when (iconNumber) {
        0 -> R.drawable.ic_sun_orange
        1 -> R.drawable.ic_tsunami_blue
        2 -> R.drawable.ic_wine_red
        3 -> R.drawable.ic_forest_green
        4 -> R.drawable.ic_sport_flag_black
        else -> R.drawable.ic_person_black
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Dzień dobry!", fontSize = 24.sp)

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            modifier = Modifier
                .size(144.dp)
        )

        Text("Łukasz Jałocha", fontSize = 24.sp)
    }
}
