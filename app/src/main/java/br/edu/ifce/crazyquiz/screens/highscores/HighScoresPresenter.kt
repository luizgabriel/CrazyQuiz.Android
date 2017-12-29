package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.util.BasePresenter

class HighScoresPresenter(view: IHighScoresView, private val store: IPlayerStore) : BasePresenter<IHighScoresView>(view) {

    override fun onCreateView() {
        view.setScoresList(
                store.sortedByDescending { p -> p.scores }.take(100)
        )
    }

}