package br.edu.ifce.crazyquiz.views

import br.edu.ifce.crazyquiz.data.QuestionOption
import br.edu.ifce.crazyquiz.presenters.BasePresenter

interface IQuestionView: BasePresenter.IView {
    fun setQuestionText(text: String)
    fun setQuestionNumber(number: Int)
    fun setQuestionOptions(options: List<QuestionOption>)
    fun setQuestionLevel(level: Int)
    fun showHint(hint: String)
    fun notifyWrongAnswer()
}