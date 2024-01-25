package com.example.jetpackcomposeproject.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackcomposeproject.data.model.Athlete
import com.example.jetpackcomposeproject.data.model.Sport
import com.example.jetpackcomposeproject.data.repository.AthleteRepository

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(navController: NavHostController) {
    val context = LocalContext.current
    val repository = AthleteRepository.getInstance(context)

    var athleteFirstName by remember { mutableStateOf("") }
    var athleteLastName by remember { mutableStateOf("") }
    var athletePhoneNumber by remember { mutableStateOf("") }
    var athleteEmail by remember { mutableStateOf("") }
    var athleteSport by remember { mutableStateOf(Sport.FOOTBALL) }
    var athleteSalary by remember { mutableFloatStateOf(0f) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = athleteFirstName,
            onValueChange = { athleteFirstName = it },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = athleteLastName,
            onValueChange = { athleteLastName = it },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = athletePhoneNumber,
            onValueChange = { athletePhoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = athleteEmail,
            onValueChange = { athleteEmail = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SportDropdown(
            selectedSport = athleteSport,
            onSportSelected = { athleteSport = it }
        )

        Text("Salary",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally))

        SalarySlider(
            salary = athleteSalary,
            onValueChange = { athleteSalary = it }
        )
        Text(
            text = "${"%.2f".format(athleteSalary)} USD",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val newAthlete = Athlete(
                    athleteFirstName,
                    athleteLastName,
                    athletePhoneNumber,
                    athleteEmail,
                    athleteSalary.toDouble(),
                    athleteSport
                )

                val result = repository.insert(newAthlete)

                if (result) {
                    Toast.makeText(context, "Athlete created successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Confirm")
        }
    }
}
