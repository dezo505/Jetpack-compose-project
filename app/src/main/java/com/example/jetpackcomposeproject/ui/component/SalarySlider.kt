package com.example.jetpackcomposeproject.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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