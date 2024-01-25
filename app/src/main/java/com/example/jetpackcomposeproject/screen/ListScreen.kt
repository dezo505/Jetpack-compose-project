package com.example.jetpackcomposeproject.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackcomposeproject.AthleteItem
import com.example.jetpackcomposeproject.Screen
import com.example.jetpackcomposeproject.data.repository.AthleteRepository

@Composable
fun ListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val repository = AthleteRepository.getInstance(context)

    var athleteList by remember { mutableStateOf(repository.findAll()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(athleteList.size) {
                AthleteItem(athleteId = athleteList[it].id, navController = navController) {
                    athleteList = repository.findAll()
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AddAthleteButton(navController)
    }
}

@Composable
fun AddAthleteButton(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.CreateScreen.route)
            },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),

        ) {
            Text("Add Athlete")
        }
    }
}
