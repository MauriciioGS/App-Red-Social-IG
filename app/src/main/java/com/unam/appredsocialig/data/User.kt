package com.unam.appredsocialig.data

data class User(
    var username: String = "",
    var email : String = "",
    var postsList: List<Post>? = null
)
