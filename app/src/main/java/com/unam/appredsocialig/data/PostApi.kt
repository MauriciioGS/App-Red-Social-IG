package com.unam.appredsocialig.data

import com.google.gson.annotations.SerializedName

data class PostApi (
    @SerializedName("id") var likes: Int,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: String,
    @SerializedName("species") var comments: String,
    @SerializedName("type") var type: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("origin") var origin: Any,
    @SerializedName("location") var location: Any,
    @SerializedName("image") var image: String,
    @SerializedName("episode") var episode: List<String>,
    @SerializedName("url") var url: String,
    @SerializedName("created") var created: String
)