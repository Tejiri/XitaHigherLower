package com.xita.games.higherlower.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.games.higherlower.dataSource.api.RetrofitInstance
import com.xita.games.higherlower.models.Card
import com.xita.games.higherlower.models.CardDeckResponse
import com.xita.games.higherlower.models.DrawCardResponse
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {
    var gameSetupLoading by mutableStateOf(true)
    var isLoading by mutableStateOf(false)
    var playerOneName by mutableStateOf("Tejiri")
    var playerTwoName by mutableStateOf("John")
    var playerOneGender by mutableStateOf("male")
    var playerTwoGender by mutableStateOf("female")
    var playerGuess by mutableStateOf("")
    var playerOneScore by mutableStateOf(0)
    var playerTwoScore by mutableStateOf(0)
    var currentCard by mutableStateOf<Card?>(null)
    var currentPlayer by mutableStateOf(1)
    var nextCard by mutableStateOf<Card?>(null)
    var currentDeck by mutableStateOf<CardDeckResponse?>(null)
    var drawCardResponse by mutableStateOf<DrawCardResponse?>(null)
    val cardValues = mapOf(
        // Spades
        "AS" to 1,
        "2S" to 2,
        "3S" to 3,
        "4S" to 4,
        "5S" to 5,
        "6S" to 6,
        "7S" to 7,
        "8S" to 8,
        "9S" to 9,
        "0S" to 10,
        "JS" to 11,
        "QS" to 12,
        "KS" to 13,
        // Hearts
        "AH" to 1,
        "2H" to 2,
        "3H" to 3,
        "4H" to 4,
        "5H" to 5,
        "6H" to 6,
        "7H" to 7,
        "8H" to 8,
        "9H" to 9,
        "0H" to 10,
        "JH" to 11,
        "QH" to 12,
        "KH" to 13,
        // Diamonds
        "AD" to 1,
        "2D" to 2,
        "3D" to 3,
        "4D" to 4,
        "5D" to 5,
        "6D" to 6,
        "7D" to 7,
        "8D" to 8,
        "9D" to 9,
        "0D" to 10,
        "JD" to 11,
        "QD" to 12,
        "KD" to 13,
        // Clubs
        "AC" to 1,
        "2C" to 2,
        "3C" to 3,
        "4C" to 4,
        "5C" to 5,
        "6C" to 6,
        "7C" to 7,
        "8C" to 8,
        "9C" to 9,
        "0C" to 10,
        "JC" to 11,
        "QC" to 12,
        "KC" to 13
    )

    var currentRoundMessage by mutableStateOf("")

//    var repository = Repository()

//    var returnCa by mutableStateOf<DrawCardResponse?>(null)


    init {
        setupDeck()
    }

    fun updatePlayer(correctGuess: Boolean) {
        if (currentPlayer == 1) {
            if (correctGuess) {
                playerOneScore++
                currentRoundMessage = "Correct"
            } else {
                currentRoundMessage = "Wrong"
            }

        } else {
            if (correctGuess) {
                playerTwoScore++
                currentRoundMessage = "Correct"
            } else {
                currentRoundMessage = "Wrong"
            }
        }
    }

    fun setNextRound() {
        currentCard = nextCard
        nextCard = null
        currentRoundMessage = ""
        playerGuess = ""
        if (currentPlayer == 1) {
            currentPlayer = 2
        } else {
            currentPlayer = 1
        }
    }

    private fun setupDeck() {
        viewModelScope.launch {
            try {
                // Create the deck
                currentDeck = RetrofitInstance.cardAPI.createDeck()
                Log.i("MYINFO", "Deck created: ${currentDeck?.deck_id}")

                // Reshuffle the deck
                currentDeck?.let {
                    reshuffle()
//                    RetrofitInstance.cardAPI.reshuffleCards(deck.deck_id, remaining = true)
//                    Log.i("MYINFO", "Deck reshuffled: ${deck.deck_id}")
                } ?: Log.e("MYINFO", "Deck is null after creation.")

                currentDeck?.let {
                    drawCard()
                    gameSetupLoading = false
                }
            } catch (e: Exception) {
                Log.e("MYINFO", "Error during setupDeck: ${e.localizedMessage}")
                gameSetupLoading = false
            }
        }
    }


    fun reshuffle() {
        viewModelScope.launch {
            RetrofitInstance.cardAPI.reshuffleCards(currentDeck!!.deck_id, remaining = true)

//            Log.i("MYINFO", currentDeck.toString())

        }
    }

    fun drawCard() {


        if (isLoading) {

        } else {
            isLoading = true
            viewModelScope.launch {
                try {
                    drawCardResponse =
                        RetrofitInstance.cardAPI.drawCard(deckId = currentDeck!!.deck_id, count = 1)
                    Log.i("MYINFO", drawCardResponse.toString())
                    if (currentCard == null) {
                        currentCard = drawCardResponse!!.cards[0]
                        isLoading = false
                    } else {
                        nextCard = drawCardResponse!!.cards[0]


                        if (cardValues[nextCard!!.code]!! > cardValues[currentCard!!.code]!! && playerGuess == "higher") {
                            updatePlayer(true)
                        } else if (cardValues[nextCard!!.code]!! < cardValues[currentCard!!.code]!! && playerGuess == "lower") {
                            updatePlayer(true)
                        } else {
                            updatePlayer(false)
                        }

                        isLoading = false
                    }
//

                } catch (e: Exception) {
                    Log.i("MYINFO", e.message.toString())
                    isLoading = false

                }
            }

        }
    }


    fun returnCards() {
        viewModelScope.launch {
            RetrofitInstance.cardAPI.returnCards(deckId = currentDeck!!.deck_id)
        }
    }
}