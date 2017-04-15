package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.CrazyQuiz
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.views.IQuestionView
import java.util.*

class QuestionPresenter(view: IQuestionView): BasePresenter<IQuestionView>(view) {

    val session = QuestionnaireSession(CrazyQuiz.questionsService)

    init {
        inflateQuestion()
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
                inflateQuestion()
            } else
                view.notifyWrongAnswer()
        } catch (e: QuestionnaireSession.FinishedGameException) {
            view.notifyRightAnswer()
            view.callFinishedGameScreen()
        } catch (e: QuestionnaireSession.GameOverException) {
            view.callGameOverScreen()
        }
    }

    private fun inflateQuestion() {
        val random = Random(System.currentTimeMillis())
        val right = IQuestionView.RightAudio.values()
        val wrong = IQuestionView.WrongAudio.values()

        view.setRightAudio(right[Math.abs(random.nextInt()) % right.size])
        view.setWrongAudio(wrong[Math.abs(random.nextInt()) % wrong.size])

        view.setQuestionNumber(session.questionNumber)
        view.setQuestionText(session.currentQuestion.text)
        view.setQuestionOptions(session.currentQuestion.options)
        view.setQuestionLevel(session.level)
        view.setLifeCount(session.life)
        view.setScoresCount(session.player.scores)
    }

    fun onClickHelp() {
        val hint = session.currentQuestion.hint
        if (hint == null)
            view.showNoHintMessage()
        else {
            session.askForHint()
            view.setScoresCount(session.player.scores)
            view.showHint(hint)
        }
    }

}