package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.net.ApiResponse
import okhttp3.RequestBody
import java.util.concurrent.Future

interface IRestClient {
    suspend fun <T> get(uri: String): ApiResponse<T>
    suspend fun <T> post(url: String, body: RequestBody): ApiResponse<T>
}