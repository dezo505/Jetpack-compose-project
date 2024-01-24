package com.example.jetpackcomposeproject

sealed class Screen(val route: String) {
    object PrimaryScreen : Screen("primary_screen")
    object ListScreen : Screen("list_screen")
    object ImageScreen : Screen("image_screen")
    object DetailsScreen : Screen("details_screen")
    object EditScreen : Screen("edit_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")

            }
        }
    }
}