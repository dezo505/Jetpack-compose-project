package com.example.jetpackcomposeproject.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.jetpackcomposeproject.AthleteItem
import com.example.jetpackcomposeproject.data.repository.AthleteRepository

@Composable
fun ListScreen(navController: NavHostController) {
    val context = LocalContext.current

    val repository = AthleteRepository.getInstance(context)

    val athleteList = repository.findAll()

    LazyColumn {
        items(athleteList.size) {
            AthleteItem(athleteList[it])
        }
    }
}