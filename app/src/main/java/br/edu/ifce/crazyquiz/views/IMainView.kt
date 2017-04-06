package br.edu.ifce.crazyquiz.views

import br.edu.ifce.crazyquiz.presenters.BasePresenter

/**
 * Created by luizg on 05/04/2017.
 */
interface IMainView : BasePresenter.IView {
    fun startGameScreen()
    fun openHighScoresScreen()
    fun openShareScreen()
}