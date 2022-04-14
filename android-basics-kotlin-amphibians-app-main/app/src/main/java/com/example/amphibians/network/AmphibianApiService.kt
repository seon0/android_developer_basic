package com.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//  Create a property for the base URL provided in the codelab
private const val BASE_URL = "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

// TODO: Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// TODO: Build a Retrofit object with the Moshi converter
val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface AmphibianApiService {
    // TODO: Declare a suspended function to get the list of amphibians
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    suspend fun getData(): List<Amphibian>
}

// TODO: Create an object that provides a lazy-initialized retrofit service
object AmphibianApi {
    val apiService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
}
