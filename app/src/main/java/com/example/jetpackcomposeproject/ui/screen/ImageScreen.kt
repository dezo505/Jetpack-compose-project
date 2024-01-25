package com.example.jetpackcomposeproject.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeproject.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageScreen() {
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    val initialPage = sharedPreferences.getInt("currentImageIndex", 0)

    val pagerState = rememberPagerState(initialPage = initialPage) { 5 }

    var currentImageIndex by remember { mutableIntStateOf(initialPage) }

    LaunchedEffect(pagerState.currentPage) {
        currentImageIndex = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            Image(
                painter = painterResource(id = when (page) {
                    0 -> R.drawable.ic_sun_orange
                    1 -> R.drawable.ic_tsunami_blue
                    2 -> R.drawable.ic_wine_red
                    3 -> R.drawable.ic_forest_green
                    4 -> R.drawable.ic_sport_flag_black
                    else -> R.drawable.ic_person_black
                }),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    sharedPreferences.edit().putInt("currentImageIndex", currentImageIndex).apply()
                    Toast.makeText(context, "Image Updated!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text("Update Image")
            }
        }
    }
}