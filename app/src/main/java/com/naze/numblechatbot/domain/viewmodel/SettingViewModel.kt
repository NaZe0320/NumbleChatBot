package com.naze.numblechatbot.domain.viewmodel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : ViewModel() {
    private val Context.myPreferences: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

    private val TEMPERATURE_KEY = doublePreferencesKey("temperature")
    private val FREQUENCY_PENALTY_KEY = doublePreferencesKey("frequency_penalty")

    // temperature 값을 저장하는 함수
    fun saveTemperature(context: Context, temperature: Double) {
        viewModelScope.launch {
            context.myPreferences.edit { preferences ->
                preferences[TEMPERATURE_KEY] = temperature
            }
        }
    }

    // temperature 값을 가져오는 함수
    fun getTemperature(context: Context): Flow<Double?> {
        return context.myPreferences.data.map { preferences ->
            preferences[TEMPERATURE_KEY]
        }
    }

    // frequencyPenalty 값을 저장하는 함수
    fun saveFrequencyPenalty(context: Context, frequencyPenalty: Double) {
        viewModelScope.launch {
            context.myPreferences.edit { preferences ->
                preferences[FREQUENCY_PENALTY_KEY] = frequencyPenalty
            }
        }
    }

    // frequencyPenalty 값을 가져오는 함수
    fun getFrequencyPenalty(context: Context): Flow<Double?> {
        return context.myPreferences.data.map { preferences ->
            preferences[FREQUENCY_PENALTY_KEY]
        }
    }

}