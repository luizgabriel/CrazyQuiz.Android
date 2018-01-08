package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.ApiResponse
import br.edu.ifce.crazyquiz.data.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class QuestionsService {

    private val client = api(QuestionsApi::class.java)

    suspend fun getQuestions(lastRefresh: Date?): ArrayList<Question> {
        return try {
            val response = client.getQuestions(formatDate(lastRefresh)).execute()
            if (response.isSuccessful)
                response.body()?.data!!
            else
                ArrayList()
        } catch (e: IOException) {
            e.printStackTrace()
            ArrayList()
        }
    }

    suspend fun notifyRightAnswer(question: Question) {
        try {
            client.notifyRightAnswer(question.id).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    suspend fun notifyWrongAnswer(question: Question) {
        try {
            client.notifyWrongAnswer(question.id).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    interface QuestionsApi {
        @GET("questions")
        fun getQuestions(@Query("last_refresh") lastRefresh: String?): Call<ApiResponse<ArrayList<Question>>>

        @POST("questions/{question}/right")
        fun notifyRightAnswer(@Path("question") questionId: Int): Call<Any>

        @POST("questions/{question}/wrong")
        fun notifyWrongAnswer(@Path("question") questionId: Int): Call<Any>
    }

}