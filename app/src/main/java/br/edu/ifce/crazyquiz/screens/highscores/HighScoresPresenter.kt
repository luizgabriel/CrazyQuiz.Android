package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.util.BasePresenter
import java.security.InvalidParameterException

class HighScoresPresenter(view: IHighScoresView, private val store: IPlayerStore) : BasePresenter<IHighScoresView>(view) {

    override fun onCreateView() {
        updateScoresList()
    }

    private fun updateScoresList() {
        view.setScoresList(
                store.sortedByDescending { p -> p.scores }.take(100)
        )
    }

    private fun add(player: Player) {
        store.add(player)
        updateScoresList()
    }

    fun onSaveScores(name: String, scores: Int) {
        if (name.trim() == "")
            throw InvalidParameterException("name")

        add(Player(name, scores))
    }

}