package com.example.jetpackcomposeproject.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackcomposeproject.R
import com.example.jetpackcomposeproject.data.model.Sport
import com.example.jetpackcomposeproject.data.repository.AthleteRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(athleteId: Int, navController: NavHostController) {
    val context = LocalContext.current
    val repository = AthleteRepository.getInstance(context)

    var athlete by remember { mutableStateOf(repository.findById(athleteId)!!) }
    var editedFirstName by remember { mutableStateOf(athlete.firstName ?: "") }
    var editedLastName by remember { mutableStateOf(athlete.lastName ?: "") }
    var editedPhoneNumber by remember { mutableStateOf(athlete.phoneNumber ?: "") }
    var editedEmail by remember { mutableStateOf(athlete.email ?: "") }
    var editedSport by remember { mutableStateOf(athlete.sport) }
    var salarySliderPosition by remember { mutableFloatStateOf(athlete.salary.toFloat()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = editedFirstName,
            onValueChange = { editedFirstName = it },
            label = { Text("First Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = editedLastName,
            onValueChange = { editedLastName = it },
            label = { Text("Last Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = editedPhoneNumber,
            onValueChange = { editedPhoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = editedEmail,
            onValueChange = { editedEmail = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SportDropdown(
            selectedSport = editedSport,
            onSportSelected = { editedSport = it }
        )

        Text("Salary",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally))

        SalarySlider(
            salary = salarySliderPosition,
            onValueChange = { salarySliderPosition = it }
        )

        Text(
            text = "${"%.2f".format(salarySliderPosition)} USD",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                athlete = athlete.copy(
                    firstName = editedFirstName,
                    lastName = editedLastName,
                    phoneNumber = editedPhoneNumber,
                    email = editedEmail,
                    salary = salarySliderPosition.toDouble(),
                    sport = editedSport
                )

                val success = repository.update(athlete)

                if (success) {
                    Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    // Handle update failure
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun SalarySlider(salary: Float, onValueChange: (Float) -> Unit) {
    Slider(
        value = salary,
        onValueChange = {
            onValueChange(it)
        },
        valueRange = 0f..20000f,
        steps = 200,
        modifier = Modifier.fillMaxWidth()
    )
}

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