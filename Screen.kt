package com.plcoding.navigationdrawercompose

sealed class Screen {
    object Home : Screen()
    object Settings : Screen()
    object Help : Screen()
    object News : Screen()
    object Resources : Screen()
}
