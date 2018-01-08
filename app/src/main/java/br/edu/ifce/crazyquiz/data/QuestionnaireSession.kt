package br.edu.ifce.crazyquiz.data

import br.edu.ifce.crazyquiz.screens.question.IQuestionStore

class QuestionnaireSession(private val questions: IQuestionStore) {

    private val skippedQuestions = ArrayList<Question>()
    private val answeredQuestions = ArrayList<Question>()
    private val questionToBonusSkip = 5
    private val livesPerQuestion = 1
    private val scoresPerQuestion = 30
    private val scoresPerError = scoresPerQuestion / 2
    private val scoresPerSkip = scoresPerError
    private var skipLimit = 3

    var life = 5
        private set(value) {
            field = if (value > 0) value else 0
            if (field == 0) throw FinishedGameException(FinishedGameMode.GameOver)
        }

    var scores: Int = 0
        private set(value) {
            field = if (value > 0) value else 0
        }

    val questionNumber get() = answeredQuestions.size + skippedQuestions.size + 1

    lateinit var currentQuestion: Question
        private set

    fun nextQuestion() {
        val q = questions.getRandomQuestion(answeredQuestions)
        if (q == null)
            throw FinishedGameException(FinishedGameMode.GameComplete)
        else
            currentQuestion = q
    }

    fun answer(selectedOption: QuestionOption): Boolean {
        return if (selectedOption.answer) {
            scores += scoresPerQuestion
            answeredQuestions.add(currentQuestion)

            if (answeredQuestions.size > 0 && (answeredQuestions.size % questionToBonusSkip) == 0)
                skipLimit += 1

            true
        } else {
            scores -= scoresPerError
            life -= livesPerQuestion

            false
        }
    }

    fun skipQuestion() {
        scores -= scoresPerSkip
        skipLimit -= 1
        skippedQuestions.add(currentQuestion)
        nextQuestion()
    }

    class FinishedGameException(val mode: FinishedGameMode) : Exception()
    enum class FinishedGameMode {
        GameOver,
        GameComplete
    }

}


