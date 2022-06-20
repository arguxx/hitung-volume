package com.d3if2022.hitungvolume.network

import com.d3if2022.hitungvolume.model.ApiModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.github.com/users/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AboutApiService {
    @GET("arguxx")
suspend fun getAbout(): ApiModel
}
object AboutApi {
    val service: AboutApiService by lazy {
        retrofit.create(AboutApiService::class.java) }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }