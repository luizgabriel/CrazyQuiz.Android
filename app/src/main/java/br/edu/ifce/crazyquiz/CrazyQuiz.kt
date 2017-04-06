package br.edu.ifce.crazyquiz

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.net.CrazyQuizRestClient
import br.edu.ifce.crazyquiz.services.IRestClient
import br.edu.ifce.crazyquiz.services.QuestionsService
import com.google.gson.Gson
import com.vincentbrison.openlibraries.android.dualcache.Builder
import com.vincentbrison.openlibraries.android.dualcache.DualCache
import okhttp3.OkHttpClient

object CrazyQuiz {

    private val CACHE_NAME = "cache.questions"
    private val APP_VERSION = 1

    private val cache = Builder<Question>(CACHE_NAME, APP_VERSION).enableLog().build()
    private val gson = Gson()
    private val http = OkHttpClient()
    private val client: IRestClient = CrazyQuizRestClient(http, gson)

    val questionsService = QuestionsService(client)


}