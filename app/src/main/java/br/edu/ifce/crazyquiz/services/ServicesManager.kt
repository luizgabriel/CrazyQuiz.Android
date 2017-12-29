package br.edu.ifce.crazyquiz.services

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> api(type: Class<T>): T {
    return ServicesManager.retrofit.create(type)
}

fun gson(): Gson {
    return ServicesManager.gson
}

fun <T> serialize(obj: T): String {
    return gson().toJson(obj, object : TypeToken<T>() {}.type)
}

fun <T> deserialize(data: String): T {
    return gson().fromJson(data, object : TypeToken<T>() {}.type)
}

object ServicesManager {

    val retrofit = Retrofit.Builder()
            .baseUrl("https://crazyquiz.herokuapp.com/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()!!

    val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()!!

}