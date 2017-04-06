package br.edu.ifce.crazyquiz.data

data class QuestionOption(val id: Int, val text: String, val questionId: Int) {
    constructor() : this(0, "", 0)
}