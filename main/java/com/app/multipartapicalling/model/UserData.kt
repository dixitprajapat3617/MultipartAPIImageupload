package com.app.multipartapicalling.model

import java.io.File

data class UserData(
    val name: String,
    val description: String,
    val image: File,
    val price: String,
    val cuisineIds: List<Int>,
    val isSpecial: String,
    val categoryId: String = "1"
)
