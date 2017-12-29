package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.ApiResponse
import br.edu.ifce.crazyquiz.data.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList


class QuestionsService {

    private val client = api(QuestionsApi::class.java)

    suspend fun getQuestions(lastRefresh: Date?): ArrayList<Question> {
        val response = client.getQuestions(lastRefresh).execute()
        return if (response.isSuccessful)
            response.body()?.data!!
        else
            ArrayList()
    }

    suspend fun notifyRightAnswer(question: Question) {
        client.notifyRightAnswer(question.id).execute()
    }

    suspend fun notifyWrongAnswer(question: Question) {
        client.notifyWrongAnswer(question.id).execute()
    }

    interface QuestionsApi {
        @GET("questions")
        fun getQuestions(@Query("last_refresh") lastRefresh: Date?): Call<ApiResponse<ArrayList<Question>>>

        @POST("questions/{question}/right")
        fun notifyRightAnswer(@Path("question") questionId: Int): Call<Any>

        @POST("questions/{question}/wrong")
        fun notifyWrongAnswer(@Path("question") questionId: Int): Call<Any>
    }

}