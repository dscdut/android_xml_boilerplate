package dev.gdsc_dut.android_xml_base_project.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.gdsc_dut.android_xml_base_project.BuildConfig
import dev.gdsc_dut.android_xml_base_project.core.AppDispatchers
import dev.gdsc_dut.android_xml_base_project.core.AppDispatchersImpl
import dev.gdsc_dut.android_xml_base_project.data.local.LocalUserDataSource
import dev.gdsc_dut.android_xml_base_project.data.remote.APIService
import dev.gdsc_dut.android_xml_base_project.data.remote.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val AUTH_DATA = "com.example.android_xml_base_project.auth_data"

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
internal annotation class BaseUrl

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun appDispatchers(impl: AppDispatchersImpl): AppDispatchers

    internal companion object {

        @Provides
        @BaseUrl
        fun baseUrl(): String = BuildConfig.BASE_URL

        @Provides
        @Singleton
        fun dataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> =
            PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                produceFile = { applicationContext.preferencesDataStoreFile(AUTH_DATA) }
            )

        @Provides
        @Singleton
        fun provideLocalUserDataSource(dataStore: DataStore<Preferences>, appDispatchers: AppDispatchers): LocalUserDataSource {
            return LocalUserDataSource(dataStore = dataStore, appDispatchers = appDispatchers)
        }

        @Provides
        @Singleton
        fun provideMoshi(): Moshi =
            Moshi
                .Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        @Provides
        @Singleton
        fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
            val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            return OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(authInterceptor)
                .addInterceptor(logging)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            moshi: Moshi,
            client: OkHttpClient,
            @BaseUrl baseUrl: String,
        ): Retrofit =
            Retrofit
                .Builder()
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseUrl)
                .build()

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): APIService = APIService(retrofit)
    }
}