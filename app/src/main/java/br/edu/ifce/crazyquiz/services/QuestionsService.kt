package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.services.IRestClient

class QuestionsService(val client: IRestClient) {

    suspend fun getRandomQuestion(answeredQuestions: List<Question>, level: Int): Question {
        val answered = answeredQuestions.map { it.id }
        val response = client.get<Question>("/questions/random?answered=" + answered.joinToString(",") + "&level=" + level )
        return response.data
    }

}