package br.edu.ifce.crazyquiz.data

import br.edu.ifce.crazyquiz.services.QuestionsService
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking

class QuestionnaireSession(val questions: QuestionsService) {

    private val skippedQuestions = ArrayList<Question>()
    private val answeredQuestions = ArrayList<Question>()
    private val questionToBonusSkip = 5
    val lifesPerQuestion = 1
    val scoresPerQuestion = 30
    val scoresPerError = scoresPerQuestion / 2
    val scoresPerHint = scoresPerError / 2
    val scoresPerSkip = scoresPerError

    val player = Player()
    val questionNumber: Int get() = answeredQuestions.size + skippedQuestions.size + 1
    val level: Int get() = if (answeredQuestions.size == 0) 1 else answeredQuestions.last().level


    lateinit var currentQuestion: Question
        private set

    var skipLimit = 3
        private set

    private var life = 5
        private set

    init {
        nextQuestion()
    }

    private fun nextQuestion() {
        runBlocking (CommonPool) {
            currentQuestion = questions.getRandomQuestion(answeredQuestions, level)
        }
    }

    fun answer(selectedOption: QuestionOption): Boolean {
        if (selectedOption.answer) {
            player.scores += scoresPerQuestion
            answeredQuestions.add(currentQuestion)
            nextQuestion()

            if (answeredQuestions.size > 0 && answeredQuestions.size % questionToBonusSkip == 0)
                skipLimit += 1

            return true
        } else {
            player.scores -= scoresPerError
            life -= lifesPerQuestion

            return false
        }
    }

    fun askForHint(): String {
        player.scores -= scoresPerHint
        return currentQuestion.hint
    }

    fun skipQuestion() {
        player.scores -= scoresPerSkip
        skipLimit -= 1
        skippedQuestions.add(currentQuestion)
        nextQuestion()
    }

}