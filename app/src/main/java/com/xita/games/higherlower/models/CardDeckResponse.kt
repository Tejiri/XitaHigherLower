package com.xita.games.higherlower.models

data class CardDeckResponse(
    val success: Boolean,
    val deck_id: String,
    val remaining: Int,
    val shuffled: Boolean
)


//{
//    "success": true,
//    "deck_id": "zar2d9qgz1lj",
//    "remaining": 52,
//    "shuffled": false
//}