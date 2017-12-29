package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.ApiResponse
import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.screens.question.IQuestionStore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList


class QuestionsService(private val store: IQuestionStore) {

    private val client = api(QuestionsApi::class.java)

    private suspend fun getQuestions(lastRefresh: Date?): ArrayList<Question> {
        val response = client.getQuestions(lastRefresh).execute()
        return if (response.isSuccessful)
            response.body()?.data!!
        else
            ArrayList()
    }

    suspend fun load(): Boolean {
        val serverQuestions = getQuestions(store.getLastRefreshDate())

        store.addAll(serverQuestions)
        return store.isNotEmpty()
    }


    fun getRandomQuestion(answeredQuestions: List<Question>): Question? {
        val answered = answeredQuestions.map { it.id }
        val levelQuestions = store.filter { it.id !in answered }

        return if (levelQuestions.isEmpty())
            null
        else {
            val randomQuestionIdx: Int = (System.currentTimeMillis() % levelQuestions.size).toInt()
            levelQuestions[randomQuestionIdx]
        }
    }

    interface QuestionsApi {
        @GET("questions")
        fun getQuestions(@Query("last_refresh") lastRefresh: Date?): Call<ApiResponse<ArrayList<Question>>>
    }

}