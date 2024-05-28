package com.dicoding.picodiploma.loginwithanimation.di

import android.content.Context
import android.os.CombinedVibration
import com.dicoding.picodiploma.loginwithanimation.api.ApiConfig
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.pref.dataStore
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getToken() }
        val apiService = ApiConfig.getApiService(user)
        return UserRepository.getInstance(apiService, pref)
    }
//    fun provideRepository(context: Context): UserRepository {
//        val pref = UserPreference.getInstance(context.dataStore)
//        return UserRepository.getInstance(pref)
//    }
}
//object Injection {
//    fun provideRepository(context: Context): UserRepository {
//        val pref = UserPreference.getInstance(context.dataStore)
//        return UserRepository.getInstance(pref)
//    }
//}