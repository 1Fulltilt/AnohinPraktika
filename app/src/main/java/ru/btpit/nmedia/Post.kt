package ru.btpit.nmedia

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    var amountlike: Int,
    var amountrepost: Int,
    var likedByMe: Boolean,
    var repostByMe: Boolean,
)
