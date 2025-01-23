package com.xita.games.higherlower.models

import com.google.gson.annotations.SerializedName

data class DrawCardResponse(
    val success: Boolean,
    @SerializedName("deck_id") val deckId: String,
    val cards: List<Card>,
    val remaining: Int
)

data class Card(
    val code: String,
    val image: String,
    val images: CardImages,
    val value: String,
    val suit: String
)

data class CardImages(
    val svg: String,
    val png: String
)