package br.edu.ifce.crazyquiz.net

import br.edu.ifce.crazyquiz.net.ApiResponse
import com.google.gson.reflect.TypeToken
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.lang.reflect.Type

interface IRestClient {
    suspend fun <T> get(uri: String, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T>
    suspend fun <T> post(url: String, body: RequestBody, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T>
}