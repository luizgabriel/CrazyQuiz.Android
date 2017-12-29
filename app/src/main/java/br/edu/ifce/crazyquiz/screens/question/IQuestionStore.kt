package br.edu.ifce.crazyquiz.screens.question

import br.edu.ifce.crazyquiz.data.Question
import java.util.*

interface IQuestionStore : Iterable<Question> {
    fun getLastRefreshDate(): Date?
    fun getRandomQuestion(answeredQuestions: List<Question>): Question?
    fun addAll(questions: Collection<Question>)
    fun isEmpty(): Boolean
}