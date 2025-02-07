package com.twitty.assignment.model

data class Book(
    val title: String,
    val link: String,
    val imageUrl: String,
    val isFavorites: Boolean = false
)

val emptyBook = Book(
    title = "Android Studio를 활용한 안드로이드 프로그래밍 (Android 12.0(S) 지원)",
    link = "https://search.shopping.naver.com/book/catalog/32436264666",
    imageUrl = "https://shopping-phinf.pstatic.net/main_3243626/32436264666.20221019104929.jpg",
    isFavorites = false
)
