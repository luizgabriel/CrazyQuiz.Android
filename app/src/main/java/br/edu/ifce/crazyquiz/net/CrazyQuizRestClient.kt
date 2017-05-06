package br.edu.ifce.crazyquiz.net

import android.util.Log.i
import br.edu.ifce.crazyquiz.data.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.lang.reflect.Type

class CrazyQuizRestClient(private val client: OkHttpClient, private val gson: Gson): IRestClient {

    val baseUrl = "https://crazyquiz.herokuapp.com/api"

    suspend override fun <T> get(uri: String, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(uri))
                .get()
                .build()

        return parseResponse(client.newCall(request).execute(), typeOfT)
    }

    suspend override fun <T> post(url: String, body: RequestBody, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T> {
        val request = Request.Builder()
                .url(path(url))
                .post(body)
                .build()

        return parseResponse(client.newCall(request).execute(), typeOfT)
    }

    private fun <T> parseResponse(response: Response, typeOfT: TypeToken<ApiResponse<T>>): ApiResponse<T> {
        return gson.fromJson<ApiResponse<T>>(response.body().charStream(), typeOfT.type)
    }

    private fun path(url: String): String {
        return baseUrl + (if (url.startsWith("/")) url else "/" + url)
    }

}
