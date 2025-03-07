package com.example.music_app.presentation.track_play.components

sealed class ActionType {
    object Play : ActionType()
    object Pause : ActionType()
    object Stop : ActionType()
    object Next : ActionType()
    object Previous : ActionType()
    data class Start(val value: Int) : ActionType()

}