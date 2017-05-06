package br.edu.ifce.crazyquiz.net

import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody

interface IRestClient {
    suspend fun <T> get(uri: String, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T>
    suspend fun <T> post(url: String, body: RequestBody, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T>
}