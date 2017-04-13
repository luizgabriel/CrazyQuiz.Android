package br.edu.ifce.crazyquiz.net

import br.edu.ifce.crazyquiz.services.IRestClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

class CrazyQuizRestClient(val client: OkHttpClient, val gson: Gson) : IRestClient {

    val baseUrl = "http://crazyquiz.herokuapp.com/api"

    override suspend fun <T> get(uri: String): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(uri))
                .get()
                .build()

        return parseReponse(client.newCall(request).execute())
    }

    override suspend fun <T> post(url: String, body: RequestBody): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(url))
                .post(body)
                .build()

        return parseReponse(client.newCall(request).execute())
    }

    fun <T> parseReponse(response: Response): ApiResponse<T> {
        val clz = object : TypeToken<ApiResponse<T>>() {}
        return gson.fromJson<ApiResponse<T>>(response.body().charStream(), clz.type)
    }

    private fun  path(url: String): String {
        return baseUrl + (if (url.startsWith("/")) url else "/" + url)
    }

}
