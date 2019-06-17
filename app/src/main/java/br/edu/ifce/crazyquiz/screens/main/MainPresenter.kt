package br.edu.ifce.crazyquiz.screens.main

import br.edu.ifce.crazyquiz.screens.question.IQuestionStore
import br.edu.ifce.crazyquiz.services.QuestionsService
import br.edu.ifce.crazyquiz.util.BasePresenter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(view: IMainView, private val store: IQuestionStore) : BasePresenter<IMainView>(view) {

    private val questions = QuestionsService()

    override fun onCreateView() {
        if (store.isEmpty())
            view.startLoading()

        GlobalScope.launch(IO) {
            val result = questions.getQuestions(store.getLastRefreshDate())
            store.addAll(result)

            GlobalScope.launch(Main) {
                view.finishLoading()
                if (store.isEmpty())
                    view.errorOnLoadQuestions()
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