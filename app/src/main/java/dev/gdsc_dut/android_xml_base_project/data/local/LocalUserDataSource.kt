package dev.gdsc_dut.android_xml_base_project.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.gdsc_dut.android_xml_base_project.core.AppDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalUserDataSource(
    private val dataStore: DataStore<Preferences>,
    private val appDispatchers: AppDispatchers,
) {
    private val jwtKey = stringPreferencesKey("jwt")
    val jwt = dataStore.data.map { pref -> pref[jwtKey]}

     suspend fun update(newValue: String) =
        withContext(appDispatchers.io) {
            dataStore.edit {
                it[jwtKey] = newValue
            }
        }
    suspend fun deleteAll() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }
}