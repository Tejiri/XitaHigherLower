package com.xita.games.higherlower.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PlayerNamesViewModel : ViewModel() {

    var playerOneName by mutableStateOf("")
    var playerTwoName by mutableStateOf("")
    var playerOneGender by mutableStateOf("male")
    var playerTwoGender by mutableStateOf("female")

    fun playerOneNameChange(newName: String) {
        playerOneName = newName
    }

    fun playerTwoNameChange(newName: String) {
        playerTwoName = newName
    }

    fun playerOneGenderChange(newGender: String) {
        playerOneGender = newGender
    }

    fun playerTwoGenderChange(newGender: String) {
        playerTwoGender = newGender
    }
}