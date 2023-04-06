package com.naze.numblechatbot.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class RequestGPT(
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("max_tokens")
    val maxTokens: Int? = null,
    @SerializedName("model")
    val model: String? = "text-davinci-003"
)
