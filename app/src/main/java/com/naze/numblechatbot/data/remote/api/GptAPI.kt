package com.naze.numblechatbot.data.remote.api

import com.naze.numblechatbot.data.remote.model.request.RequestGPT
import com.naze.numblechatbot.data.remote.model.response.ResponseGPT
import retrofit2.http.*

interface GptAPI {
    @POST("/v1/completions")
    suspend fun question(
        @Body request: RequestGPT
    ) : ResponseGPT
}