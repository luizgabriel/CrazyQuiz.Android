package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.views.IMainView

class MainPresenter(view: IMainView) : BasePresenter<IMainView>(view) {

    fun onClickStartGame() {
        view.startGameScreen()
    }

    fun onClickHighScores() {
        view.openHighScoresScreen()
    }

    fun onClickShare() {
        view.openShareScreen()
    }

}