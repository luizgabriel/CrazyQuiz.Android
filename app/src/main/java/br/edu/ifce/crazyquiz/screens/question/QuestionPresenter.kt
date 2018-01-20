package br.edu.ifce.crazyquiz.screens.question

import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameComplete
import br.edu.ifce.crazyquiz.data.QuestionnaireSession.FinishedGameMode.GameOver
import br.edu.ifce.crazyquiz.services.QuestionsService
import br.edu.ifce.crazyquiz.util.BasePresenter
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import java.util.*

class QuestionPresenter(view: IQuestionView, questionsStore: IQuestionStore) : BasePresenter<IQuestionView>(view) {

    private val session = QuestionnaireSession(questionsStore)
    private val service = QuestionsService()

    private val wrongAudios = arrayListOf(
            R.raw.wrong_buzzer,
            R.raw.wrong_derp,
            R.raw.wrong_fart,
            R.raw.wrong_nope,
            R.raw.wrong_really_nigga,
            R.raw.wrong_scream
    )

    private val rightAudios = arrayListOf(
            R.raw.right_cheering,
            R.raw.right_ding,
            R.raw.right_heavenly
    )

    override fun onCreateView() {
        bindQuestion()
    }

    fun onBackButtonPressed() {
        view.finish()
    }

    fun onClickOption(number: Int) {
        try {

            val result = session.answer(session.currentQuestion.options[number])
            view.setLifeCount(session.life)
            view.setScoresCount(session.scores)

            if (result) {
                view.notifyRightAnswer()
                bindQuestion()
            } else {
                view.notifyWrongAnswer()
            }

            launch(CommonPool) {
                if (result)
                    service.notifyRightAnswer(session.currentQuestion)
                else
                    service.notifyWrongAnswer(session.currentQuestion)
            }

        } catch (e: QuestionnaireSession.FinishedGameException) {
            when (e.mode) {
                GameOver -> view.notifyWrongAnswer()
                GameComplete -> view.notifyRightAnswer()
            }

            view.callFinishedGameScreen(session.scores, e.mode)
        }
    }

    private fun bindQuestion() {
        session.nextQuestion()

        val random = Random(System.currentTimeMillis())
        view.setRightAudio(rightAudios[Math.abs(random.nextInt()) % rightAudios.size])
        view.setWrongAudio(wrongAudios[Math.abs(random.nextInt()) % wrongAudios.size])

        view.setQuestionNumber(session.questionNumber)
        view.setQuestionText(session.currentQuestion.text)
        view.setQuestionOptions(session.currentQuestion.options)
        updateCounters()
    }

    private fun updateCounters() {
        view.setLifeCount(session.life)
        view.setScoresCount(session.scores)
    }

}