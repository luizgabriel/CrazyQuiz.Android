package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.CrazyQuiz
import br.edu.ifce.crazyquiz.data.QuestionarySession
import br.edu.ifce.crazyquiz.views.IQuestionView

class QuestionPresenter(view: IQuestionView): BasePresenter<IQuestionView>(view) {

    val session = QuestionarySession(CrazyQuiz.questionsService)



}