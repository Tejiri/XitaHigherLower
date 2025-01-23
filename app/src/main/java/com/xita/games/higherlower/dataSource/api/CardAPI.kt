package com.xita.games.higherlower.dataSource.api

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.xita.games.higherlower.models.CardDeckResponse
import com.xita.games.higherlower.models.DrawCardResponse
import com.xita.games.higherlower.models.ReshuffleCardResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardAPI {

    // Create a new deck
    @GET("new")
    suspend fun createDeck(
        @Query("jokers_enabled") jokersEnabled: Boolean = false // Optional query for enabling jokers
    ): CardDeckResponse

    // Draw cards from a specific deck
    @GET("{deckId}/draw")
    suspend fun drawCard(
        @Path("deckId") deckId: String,
        @Query("count") count: Int
    ): DrawCardResponse

    // Reshuffle a specific deck
    @GET("{deckId}/shuffle")
    suspend fun reshuffleCards(
        @Path("deckId") deckId: String,
        @Query("remaining") remaining: Boolean
    ): ReshuffleCardResponse

    // Return cards to the deck
    @GET("{deckId}/return")
    suspend fun returnCards(
        @Path("deckId") deckId: String
    )
}