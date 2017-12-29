package br.edu.ifce.crazyquiz.screens.main

import br.edu.ifce.crazyquiz.screens.question.IQuestionStore
import br.edu.ifce.crazyquiz.services.QuestionsService
import br.edu.ifce.crazyquiz.util.BasePresenter
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainPresenter(view: IMainView, store: IQuestionStore) : BasePresenter<IMainView>(view) {

    private val questions = QuestionsService(store)

    override fun onCreateView() {
        view.startLoading()
        launch(CommonPool) {
            val result = questions.load()

            launch(UI) {
                view.finishLoading()
                if (!result) view.errorOnLoadQuestions()
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