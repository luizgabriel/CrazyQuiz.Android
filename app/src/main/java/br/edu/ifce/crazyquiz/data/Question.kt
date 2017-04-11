package br.edu.ifce.crazyquiz.data

data class Question(
        val id: Int,
        val text: String,
        val rightOptionId: Int,
        val options: List<QuestionOption> = ArrayList<QuestionOption>(),
        val level: Int = 1, val hint: String = "")
