package br.edu.ifce.crazyquiz.data

import br.edu.ifce.crazyquiz.services.QuestionsService

class QuestionnaireSession(private val questions: QuestionsService) {

    private val skippedQuestions = ArrayList<Question>()
    private val answeredQuestions = ArrayList<Question>()
    private val questionToBonusSkip = 5
    private val livesPerQuestion = 1
    private val scoresPerQuestion = 30
    private val scoresPerError = scoresPerQuestion / 2
    private val scoresPerSkip = scoresPerError
    private var skipLimit = 3

    val player = Player()
    val questionNumber: Int get() = answeredQuestions.size + skippedQuestions.size + 1

    lateinit var currentQuestion: Question
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
        val q = questions.getRandomQuestion(answeredQuestions)
        if (q == null)
            throw FinishedGameException(this)
        else
            currentQuestion = q
    }

    fun answer(selectedOption: QuestionOption): Boolean {
        return if (selectedOption.answer) {
            player.scores += scoresPerQuestion
            answeredQuestions.add(currentQuestion)
            nextQuestion()

            if (answeredQuestions.size > 0 && (answeredQuestions.size % questionToBonusSkip) == 0)
                skipLimit += 1

            true
        } else {
            player.scores -= scoresPerError
            life -= livesPerQuestion

            false
        }
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


