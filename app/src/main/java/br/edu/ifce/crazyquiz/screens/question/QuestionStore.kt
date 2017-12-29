package br.edu.ifce.crazyquiz.screens.question

import android.content.Context
import br.edu.ifce.crazyquiz.data.Question
import br.edu.ifce.crazyquiz.services.deserialize
import br.edu.ifce.crazyquiz.services.serialize
import java.util.*
import kotlin.collections.ArrayList

class QuestionStore(context: Context): IQuestionStore {

    private val sharedPreferences = context.getSharedPreferences("QuestionStore", Context.MODE_PRIVATE)
    private val cache = LinkedList<Question>()
    private val questionPrefKey = "questions"

    init {
        val storedQuestionsData = sharedPreferences.getString(questionPrefKey, "[]")
        cache += deserialize<ArrayList<Question>>(storedQuestionsData)
    }

    override fun getLastRefreshDate(): Date? {
        val key = "lastRefreshDate"
        val dateTime = sharedPreferences.getLong(key, 0L)

        sharedPreferences.edit().putLong(key, Date().time).apply()

        return if (dateTime > 0) Date(dateTime) else null
    }

    override fun addAll(questions: Collection<Question>) {
        cache.addAll(questions)
        sharedPreferences.edit().putString(questionPrefKey, serialize(cache)).apply()
    }

    override fun isNotEmpty(): Boolean {
        return cache.isNotEmpty()
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Question> {
        return cache.iterator()
    }

}