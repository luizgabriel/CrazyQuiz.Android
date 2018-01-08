package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.data.QuestionnaireSession
import br.edu.ifce.crazyquiz.util.BasePresenter

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
        if (name.trim() == "") return
        add(Player(name, scores))
    }

    fun onGameFinished(scores: Int, mode: QuestionnaireSession.FinishedGameMode?) {
        if (scores > 0)
            view.showGameFinishedDialog(Player(store.getLastPlayerName(), scores), mode!!)
    }

}