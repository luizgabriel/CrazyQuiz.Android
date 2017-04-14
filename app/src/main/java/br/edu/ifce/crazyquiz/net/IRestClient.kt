package br.edu.ifce.crazyquiz.net

import br.edu.ifce.crazyquiz.net.ApiResponse
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

interface IRestClient {
    suspend fun <T> get(uri: String): ApiResponse<T>
    suspend fun <T> post(url: String, body: RequestBody): ApiResponse<T>
}