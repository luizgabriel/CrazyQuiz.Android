package br.edu.ifce.crazyquiz.data

import br.edu.ifce.crazyquiz.services.QuestionsService
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking

class QuestionarySession(val questions: QuestionsService) {

    private val answeredQuestions = ArrayList<Question>()
    private lateinit var currentQuestion: Question
    private var life = 5
    private var skipLimit = 3
    private val questionToBonusSkip = 5
    private val lifesPerQuestion = 1
    private val scoresPerQuestion = 30
    private val scoresPerError = scoresPerQuestion / 2
    private val scoresPerHint = scoresPerError / 2
    private val scoresPerSkip = scoresPerError

    val level: Int get() = if (answeredQuestions.size == 0) 1 else answeredQuestions.last().level

    val player = Player()

    init {
        nextQuestion()
    }

    private fun nextQuestion() {
        runBlocking (CommonPool) {
            currentQuestion = questions.getRandomQuestion(answeredQuestions, level)
        }
    }

    fun answer(selectedOption: QuestionOption) {
        if (currentQuestion.rightOptionId == selectedOption.id) {
            player.scores += scoresPerQuestion
            answeredQuestions.add(currentQuestion)
            nextQuestion()

            if (answeredQuestions.size > 0 && answeredQuestions.size % questionToBonusSkip == 0)
                skipLimit += 1

        } else {
            player.scores -= scoresPerError
            life -= lifesPerQuestion
        }
    }

    fun askForHint() {
        player.scores -= scoresPerHint
    }

    fun skipQuestion() {
        player.scores -= scoresPerSkip
        skipLimit -= 1
        nextQuestion()
    }

}