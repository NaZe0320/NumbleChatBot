package com.naze.numblechatbot.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ChoicesModel(
    @SerializedName("text") val text: String,
    @SerializedName("index") val index: Int,
    @SerializedName("logprobs") val logprobs: Any? = null,
    @SerializedName("finish_reason") val finishReason: String? = null)
{
}