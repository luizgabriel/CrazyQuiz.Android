package br.edu.ifce.crazyquiz

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.net.CrazyQuizRestClient
import br.edu.ifce.crazyquiz.net.IRestClient
import br.edu.ifce.crazyquiz.services.QuestionsService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient

object CrazyQuiz {

    private val gson = GsonBuilder().create()
    private val http = OkHttpClient()
    private val client: IRestClient = CrazyQuizRestClient(http, gson)

    val questionsService = QuestionsService(client)

}