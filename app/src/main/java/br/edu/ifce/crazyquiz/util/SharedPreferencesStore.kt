package br.edu.ifce.crazyquiz.util

import android.content.Context
import br.edu.ifce.crazyquiz.services.deserialize
import br.edu.ifce.crazyquiz.services.serialize
import kotlin.collections.ArrayList

abstract class SharedPreferencesStore<T>(context: Context, private val prefKey: String): Iterable<T> {

    protected val sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)!!
    protected val cache: ArrayList<T> by lazy {
        val storedQuestionsData = sharedPreferences.getString(prefKey, "{}")
        deserialize<ArrayList<T>>(storedQuestionsData)
    }

    fun saveChanges() {
        sharedPreferences.edit().putString(prefKey, serialize(cache)).apply()
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> {
        return cache.iterator()
    }

}