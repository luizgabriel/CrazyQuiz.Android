package br.edu.ifce.crazyquiz.screens.question

import android.content.Context
import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.util.SharedPreferencesStore
import java.util.*

class QuestionStore(context: Context) : SharedPreferencesStore<Question>(context, "questions"), IQuestionStore {

    override fun getLastRefreshDate(): Date? {
        val key = "lastRefreshDate"
        val dateTime = sharedPreferences.getLong(key, 0L)

        sharedPreferences.edit().putLong(key, Date().time).apply()

        return if (dateTime > 0) Date(dateTime) else null
    }

    override fun getRandomQuestion(answeredQuestions: List<Question>): Question? {
        val answered = answeredQuestions.map { it.id }
        val levelQuestions = cache.filter { it.id !in answered }

        return if (levelQuestions.isEmpty())
            null
        else {
            val randomQuestionIdx: Int = (System.currentTimeMillis() % levelQuestions.size).toInt()
            levelQuestions[randomQuestionIdx]
        }
    }

    override fun addAll(questions: Collection<Question>) {
        cache.addAll(questions.distinctBy { q -> q.id })
        saveChanges()
    }

    override fun isEmpty(): Boolean {
        return cache.isEmpty()
    }

}