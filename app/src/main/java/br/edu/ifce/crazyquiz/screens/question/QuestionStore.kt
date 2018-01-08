package br.edu.ifce.crazyquiz.screens.question

import android.content.Context
import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.util.SharedPreferencesStore
import com.google.gson.reflect.TypeToken
import java.util.*

class QuestionStore(context: Context) : SharedPreferencesStore<Question>(context, object : TypeToken<ArrayList<Question>>() {}), IQuestionStore {

    override fun getLastRefreshDate(): Date? {
        val key = "lastRefreshDate"
        val currentTime = Date().time
        val lastRefreshTime = sharedPreferences.getLong(key, currentTime)
        val diffInDays = ((currentTime - lastRefreshTime) / (1000 * 60 * 60 * 24)).toInt()

        if (diffInDays > 1)
            sharedPreferences.edit().putLong(key, currentTime).apply()

        return if (diffInDays > 0) Date(lastRefreshTime) else null
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
        cache.addAll(questions)
        val temp = cache.distinctBy { q -> q.id }
        cache.clear()
        cache.addAll(temp)

        saveChanges()
    }

    override fun isEmpty(): Boolean {
        return cache.isEmpty()
    }

}