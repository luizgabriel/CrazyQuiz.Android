package br.edu.ifce.crazyquiz.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class Question(
        val id: Int,
        val text: String,
        val level: Int = 1,
        val hint: String = "",
        val options: List<QuestionOption> = emptyList()) {

    constructor() : this(0, "") {
        //Used for deserialization
    }

}


