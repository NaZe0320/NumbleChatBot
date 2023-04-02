package com.naze.numblechatbot.data.local.model

import androidx.room.Entity

@Entity(tableName = "setting")
data class Setting(
    var temperature: Double = 1.0,
    var frequencyPenalty: Double = 0.0,
) {
    @JvmName("setTemperature1")
    fun setTemperature(num : Double) {
        if (num > 2.0) temperature = 2.0
        else if (num < 0.0) temperature = 0.0
    }

    @JvmName("setFrequency1")
    fun setFrequency(num : Double) {
        if (num > 2.0) frequencyPenalty = 2.0
        else if (num < -2.0) frequencyPenalty = -2.0
    }
}
