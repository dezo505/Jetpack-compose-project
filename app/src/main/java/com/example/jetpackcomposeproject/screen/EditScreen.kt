package com.example.jetpackcomposeproject.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun EditScreen(id: Long, navController: NavHostController) {
    Text("Edit screen id: $id")
}