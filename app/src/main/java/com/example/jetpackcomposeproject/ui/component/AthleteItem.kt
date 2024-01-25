package com.example.jetpackcomposeproject.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposeproject.R
import com.example.jetpackcomposeproject.ui.navigation.Screen
import com.example.jetpackcomposeproject.data.model.Athlete
import com.example.jetpackcomposeproject.data.repository.AthleteRepository

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AthleteItem(athleteId: Int, navController: NavController, onDeleteConfirmed: () -> Unit) {

    val context = LocalContext.current

    val repository = AthleteRepository.getInstance(context)

    val athlete = repository.findById(athleteId)!!

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
            .combinedClickable (
                onClick = {
                    navController.navigate(Screen.DetailsScreen.withArgs(athlete.id.toString()))
                },
                onLongClick = {
                    showDialog = true
                }
            ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = athlete.sport?.iconResId ?: R.drawable.ic_cross_grey),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${athlete.firstName} ${athlete.lastName}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val salaryIcon = when {
                    athlete.salary < 3000 -> R.drawable.ic_poor_red
                    athlete.salary in 3000.0..8000.0 -> R.drawable.ic_rich_green
                    else -> R.drawable.ic_mega_rich_blue
                }

                Image(
                    painter = painterResource(id = salaryIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
                Text(text = "${"%.2f".format(athlete.salary)} USD", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

    if (showDialog) {
        DeleteConfirmationDialog(
            athlete = athlete,
            onConfirm = {
                repository.remove(athlete)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            },
            onDeleteConfirmed = onDeleteConfirmed
        )
    }
}

@Composable
fun DeleteConfirmationDialog(
    athlete: Athlete,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDeleteConfirmed: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Confirm Deletion")
        },
        text = {
            Text(text = "Are you sure you want to delete ${athlete.firstName} ${athlete.lastName}?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDeleteConfirmed()
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Delete", color = LocalContentColor.current)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Cancel", color = LocalContentColor.current)
            }
        }
    )
}

