package com.naze.numblechatbot.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ResponseGPT(
    @SerializedName("choices") val choices: List<ChoicesModel>
)
