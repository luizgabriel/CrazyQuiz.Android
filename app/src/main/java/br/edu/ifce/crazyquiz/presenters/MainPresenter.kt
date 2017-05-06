package br.edu.ifce.crazyquiz.presenters

import br.edu.ifce.crazyquiz.CrazyQuiz.questionsService
import br.edu.ifce.crazyquiz.views.IMainView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainPresenter(view: IMainView) : BasePresenter<IMainView>(view) {

    init {
        view.startLoading()
        launch(CommonPool) {
            val res = questionsService.retrieveQuestions()

            launch(UI) {
                view.finishLoading()
                if (!res) view.errorOnLoadQuestions()
            }
        }
    }

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