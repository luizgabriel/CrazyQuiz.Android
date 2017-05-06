package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.net.ApiResponse
import br.edu.ifce.crazyquiz.net.IRestClient
import com.google.gson.reflect.TypeToken
import java.net.ConnectException
import java.util.*
import kotlin.collections.ArrayList

class QuestionsService(val client: IRestClient) {

    val questions = LinkedList<Question>()

    private suspend fun loadQuestions(level: Int = 1) {
        val responseType = object: TypeToken<ApiResponse<ArrayList<Question>>>() {}
        val response = client.get("questions?level=%d".format(level), responseType)
        for (q: Question in response.data)
            questions.addLast(q);

        if (response.size > 0)
            loadQuestions(level+1)
    }

    fun getRandomQuestion(answeredQuestions: List<Question>, level: Int): Question? {
        val answered = answeredQuestions.map { it.id }
        val levelQuestions = questions.filter {it.level == level && it.id !in answered }

        if (levelQuestions.isEmpty())
            return null
        else {
            val randomQuestionIdx: Int = (System.currentTimeMillis() % levelQuestions.size).toInt()
            return levelQuestions[randomQuestionIdx]
        }
    }

    suspend fun retrieveQuestions(): Boolean {
        try {
            loadQuestions()
            return questions.size > 0
        } catch (e: ConnectException) {
            return false
        }
    }

}