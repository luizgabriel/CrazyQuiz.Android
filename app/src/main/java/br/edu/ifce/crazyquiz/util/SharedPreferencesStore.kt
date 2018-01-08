package br.edu.ifce.crazyquiz.util

import android.content.Context
import br.edu.ifce.crazyquiz.services.deserialize
import br.edu.ifce.crazyquiz.services.serialize
import com.google.gson.reflect.TypeToken

abstract class SharedPreferencesStore<T>(context: Context, private val typeToken: TypeToken<ArrayList<T>>) : Iterable<T> {

    private val prefKey = typeToken.toString()
    protected val sharedPreferences by lazy { context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)!! }
    protected val cache by lazy {
        val storedQuestionsData = sharedPreferences.getString(prefKey, "[]")
        deserialize<ArrayList<T>>(storedQuestionsData, typeToken.type)
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