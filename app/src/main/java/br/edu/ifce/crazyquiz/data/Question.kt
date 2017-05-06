package br.edu.ifce.crazyquiz.data

data class Question(
        val id: Int,
        val text: String,
        val level: Int,
        val hint: String?,
        val options: ArrayList<QuestionOption>)

