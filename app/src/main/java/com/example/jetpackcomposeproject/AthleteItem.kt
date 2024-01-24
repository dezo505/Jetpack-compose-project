package com.example.jetpackcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeproject.data.model.Athlete
import com.example.jetpackcomposeproject.data.model.Sport
import kotlin.math.roundToInt

@Composable
fun AthleteItem(athlete: Athlete) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        shape = RoundedCornerShape(16.dp)
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
                Text(text = "${athlete.salary.roundToInt()} USD", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
@Preview
fun AthleteItemPreview() {
    val sampleAthlete = Athlete(
        firstName = "John",
        lastName = "Doe",
        phoneNumber = "123-456-7890",
        email = "john.doe@example.com",
        salary = 5000.0,
        sport = Sport.RUNNING
    )
    AthleteItem(athlete = sampleAthlete)
}
