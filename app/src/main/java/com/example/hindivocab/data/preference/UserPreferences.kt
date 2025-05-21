//package com.example.hindivocab.data.preference
//
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.intPreferencesKey
//import androidx.datastore.preferences.core.longPreferencesKey
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import java.util.Calendar
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class UserPreferences @Inject constructor(
//    private val dataStore: DataStore<Preferences>
//) {
//    private val LAST_UPDATE_DATE = longPreferencesKey("last_update_date")
//    private val CURRENT_WORD_ID = intPreferencesKey("current_word_id")
//
//    val currentWordId: Flow<Int> = dataStore.data.map { preference ->
//        preference[CURRENT_WORD_ID] ?: 0
//    }
//
//    val shouldUpdateDailyWord: Flow<Boolean> = dataStore.data.map { preference ->
//        val lastUpdateTime = preference[LAST_UPDATE_DATE] ?: 0
//        val currentTime = Calendar.getInstance().timeInMillis
//        val daysDifference = TimeUnit.MILLISECONDS.toDays(currentTime - lastUpdateTime)
//        daysDifference >= 1
//    }
//
//    suspend fun updateDailyWord(wordId: Int) {
//        dataStore.edit { preferences ->
//            preferences[LAST_UPDATE_DATE] = Calendar.getInstance().timeInMillis
//            preferences[CURRENT_WORD_ID] = wordId
//        }
//    }
//}
