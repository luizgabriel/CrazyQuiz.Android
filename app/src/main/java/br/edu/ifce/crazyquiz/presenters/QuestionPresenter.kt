package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.CrazyQuiz
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.views.IQuestionView

class QuestionPresenter(view: IQuestionView): BasePresenter<IQuestionView>(view) {

    val session = QuestionnaireSession(CrazyQuiz.questionsService)

    init {
        inflateQuestion()
    }

    fun onClickOption(number: Int) {
        val result = session.answer(session.currentQuestion.options[number])
        if (result)
            inflateQuestion()
        else
            view.notifyWrongAnswer()
    }

    private fun inflateQuestion() {
        view.setQuestionNumber(session.questionNumber)
        view.setQuestionText(session.currentQuestion.text)
        view.setQuestionOptions(session.currentQuestion.options)
        view.setQuestionLevel(session.level)
    }

    fun onClickHelp() {
        view.showHint(session.askForHint())
    }

}