package com.dicoding.picodiploma.loginwithanimation.api

import com.dicoding.picodiploma.loginwithanimation.api.Responses.AddStoryResponse
import com.dicoding.picodiploma.loginwithanimation.api.Responses.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.api.Responses.RegisterResponse
import com.dicoding.picodiploma.loginwithanimation.api.Responses.StoryResponse
import com.dicoding.picodiploma.loginwithanimation.api.Responses.ListStoryItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    suspend fun getStories(): StoryResponse

    @Multipart
    @POST("stories")
    fun postStory(
        @Part("description") description: String,
        @Part photo: MultipartBody.Part
    ): Call<AddStoryResponse>

}