package com.example.jetpackcomposeproject.screen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PrimaryScreen() {
    val context = LocalContext.current

    val iconNumber by remember {
        derivedStateOf {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            sharedPreferences.getInt("iconNumber", 0)
        }
    }

    val icon = when (iconNumber) {
        1 -> Icons.Filled.Favorite
        2 -> Icons.Filled.Star
        3 -> Icons.Filled.ThumbUp
        else -> Icons.Filled.Person
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Dzień dobry!", fontSize = 24.sp)

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(144.dp)
        )

        Text("Łukasz Jałocha", fontSize = 24.sp)
    }
}
