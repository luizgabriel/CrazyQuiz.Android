package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.CrazyQuiz
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.views.IQuestionView

class QuestionPresenter(view: IQuestionView): BasePresenter<IQuestionView>(view) {

    val session = QuestionnaireSession(CrazyQuiz.questionsService)

    init {
        view.setQuestionNumber(session.questionNumber)
        view.setQuestionText(session.currentQuestion.text)
        view.setQuestionOptions(session.currentQuestion.options)
    }

    fun onClickOption(number: Int) {
        session.answer(session.currentQuestion.options[number])
    }

    fun onClickHelp() {
        view.showHint(session.currentQuestion.hint)
    }

}