package br.edu.ifce.crazyquiz.views

import br.edu.ifce.crazyquiz.presenters.BasePresenter

interface IMainView : BasePresenter.IView {
    fun startGameScreen()
    fun openHighScoresScreen()
    fun openShareScreen()
    fun errorOnLoadQuestions()
    fun startLoading()
    fun finishLoading()
}