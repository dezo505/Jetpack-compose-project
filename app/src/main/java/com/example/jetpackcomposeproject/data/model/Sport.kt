package com.example.jetpackcomposeproject.data.model

import com.example.jetpackcomposeproject.R

enum class Sport(val iconResId: Int, val fullName: String) {
    RUNNING(R.drawable.ic_running_green, "Running"),
    SWIMMING(R.drawable.ic_swimming_blue, "Swimming"),
    FOOTBALL(R.drawable.ic_football_black, "Football"),
    SURFING(R.drawable.ic_surfing_blue, "Surfing"),
    BASKETBALL(R.drawable.ic_basketball_orange, "Basketball")
}