package br.edu.ifce.crazyquiz

import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.net.CrazyQuizRestClient
import br.edu.ifce.crazyquiz.services.IRestClient
import br.edu.ifce.crazyquiz.services.QuestionsService
import com.google.gson.Gson
import com.vincentbrison.openlibraries.android.dualcache.Builder
import com.vincentbrison.openlibraries.android.dualcache.DualCache
import com.vincentbrison.openlibraries.android.dualcache.SizeOf
import okhttp3.OkHttpClient

object CrazyQuiz {

    private val gson = Gson()
    private val http = OkHttpClient()
    private val client: IRestClient = CrazyQuizRestClient(http, gson)

    val questionsService = QuestionsService(client)

}