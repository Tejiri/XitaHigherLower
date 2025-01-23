package com.xita.games.higherlower.models

data class ReshuffleCardResponse(
    val success: Boolean,
    val deck_id: String,
    val remaining: Int,
    val shuffled: Boolean
)
//
//{
//    "success": true,
//    "deck_id": "5d9s0getzecg",
//    "remaining": 52,
//    "shuffled": true
//}