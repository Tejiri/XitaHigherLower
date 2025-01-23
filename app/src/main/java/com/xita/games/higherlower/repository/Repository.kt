//package com.xita.games.higherlower.repository
//
//import android.util.Log
//import androidx.lifecycle.viewModelScope
//import com.xita.games.higherlower.dataSource.api.RetrofitInstance
//import com.xita.games.higherlower.models.CardDeckResponse
//import kotlinx.coroutines.launch
//
//class Repository {
//
////
////    fun reshuffle() {
////        viewModelScope.launch {
////            RetrofitInstance.CardAPI.reshuffleCards(currentDeck!!.deck_id, remaining = true)
////
////        }
////    }
////
////    fun drawCard() {
////        viewModelScope.launch {
////            currentDeck?.let {
////                drawCardResponse = RetrofitInstance.CardAPI.drawCard(deckId = it.deck_id, count = 1)
////            }
////        }
////    }
////
////    fun returnCards() {
////        viewModelScope.launch {
////            RetrofitInstance.CardAPI.returnCards(deckId = currentDeck!!.deck_id)
////        }
////    }
//
//    suspend fun createDeck(): CardDeckResponse {
//        return RetrofitInstance.CardAPI.createDeck()
//    }
////
////    suspend fun drawCard(): List<Account> {
////        return RetrofitInstance.CardAPI.drawCard(jokersEnabled = false)
////    }
////
////    suspend fun logUserIn(userLogin: UserLogin): UserResponse {
////        return RetrofitInstance.chadventAPI.logUserIn(
////            apiKey = api_key,
////            username = userLogin.username,
////            password = userLogin.password
////        )
////    }
//}
