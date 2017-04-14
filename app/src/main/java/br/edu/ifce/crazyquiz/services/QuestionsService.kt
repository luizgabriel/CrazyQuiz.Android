package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.net.ApiResponse
import br.edu.ifce.crazyquiz.net.IRestClient
import com.google.gson.Gson

class QuestionsService(val client: IRestClient) {

    suspend fun getRandomQuestion(answeredQuestions: List<Question>, level: Int): Question {
        val answered = answeredQuestions.map { it.id }
        val response = client.get<Question>("/questions/random?answered={0}&level={1}".format(answered.joinToString(","), level))

        return response.data
    }

}