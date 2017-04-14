package br.edu.ifce.crazyquiz.net

import br.edu.ifce.crazyquiz.data.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response


class CrazyQuizRestClient(private val client: OkHttpClient, private val gson: Gson): IRestClient {

    val baseUrl = "http://crazyquiz.herokuapp.com/api"

    suspend override fun <T> get(uri: String): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(uri))
                .get()
                .build()

        return parseResponse(client.newCall(request).execute())
    }

    suspend override fun <T> post(url: String, body: RequestBody): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(url))
                .post(body)
                .build()

        return parseResponse(client.newCall(request).execute())
    }

    private fun <T> parseResponse(response: Response): ApiResponse<T> {
        val type = object : TypeToken<ApiResponse<Question>>() {}.type
        return gson.fromJson(response.body().charStream(), type)
    }

    private fun path(url: String): String {
        return baseUrl + (if (url.startsWith("/")) url else "/" + url)
    }

}
