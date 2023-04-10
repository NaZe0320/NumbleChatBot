package com.naze.numblechatbot.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class RequestGPT(
    @SerializedName("prompt") val prompt: String,
    @SerializedName("max_tokens") val maxTokens: Int? = 3900,
    @SerializedName("model") val model: String? = "text-davinci-003",
    @SerializedName("temperature") val temperature: Double?,
    @SerializedName("frequency_penalty") val frequencyPenalty: Double?,
    @SerializedName("presence_penalty") val presencePenalty: Int = 0,
    @SerializedName("top_p") val topP : Int = 1
)
