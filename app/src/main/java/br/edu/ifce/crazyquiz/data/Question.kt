package br.edu.ifce.crazyquiz.data

data class Question(
        val id: Int,
        val text: String,
        val level: Int = 1,
        val hint: String? = null,
        val options: List<QuestionOption> = emptyList())


