package br.edu.ifce.crazyquiz.screens.question

import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.services.QuestionsService
import br.edu.ifce.crazyquiz.util.BasePresenter
import java.util.*

class QuestionPresenter(view: IQuestionView, store: IQuestionStore) : BasePresenter<IQuestionView>(view) {

    private val session = QuestionnaireSession(QuestionsService(store))

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
            view.setScoresCount(session.player.scores)

            if (result) {
                view.notifyRightAnswer()
                bindQuestion()
            } else
                view.notifyWrongAnswer()
        } catch (e: QuestionnaireSession.FinishedGameException) {
            view.notifyRightAnswer()
            view.callFinishedGameScreen()
        } catch (e: QuestionnaireSession.GameOverException) {
            view.callGameOverScreen()
        }
    }

    private fun bindQuestion() {
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
        view.setScoresCount(session.player.scores)
    }

}