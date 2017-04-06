package br.edu.ifce.crazyquiz.data

data class Question(val id: Int, val text: String, val rightOptionId: Int, val level: Int, val hint: String = "") {
    constructor() : this(0, "", 0)
}