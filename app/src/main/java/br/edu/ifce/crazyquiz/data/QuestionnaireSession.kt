package br.edu.ifce.crazyquiz.data

import br.edu.ifce.crazyquiz.services.QuestionsService

class QuestionnaireSession(val questions: QuestionsService) {

    private val skippedQuestions = ArrayList<Question>()
    private val answeredQuestions = ArrayList<Question>()
    private val questionToBonusSkip = 5
    val livesPerQuestion = 1
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

    var life = 5
        private set(value) {
            field = if (value > 0) value else 0
            if (field == 0) throw GameOverException(this)
        }

    init {
        nextQuestion()
    }

    private fun nextQuestion() {
        val q = questions.getRandomQuestion(answeredQuestions, level)
        if (q == null)
            throw FinishedGameException(this@QuestionnaireSession)
        else
            currentQuestion = q
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
            life -= livesPerQuestion

            return false
        }
    }

    fun askForHint() {
        player.scores -= scoresPerHint
    }

    fun skipQuestion() {
        player.scores -= scoresPerSkip
        skipLimit -= 1
        skippedQuestions.add(currentQuestion)
        nextQuestion()
    }

    class GameOverException(val session: QuestionnaireSession) : Exception()
    class FinishedGameException(val session: QuestionnaireSession) : Exception()

}


