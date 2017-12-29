package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.services.PlayersService
import br.edu.ifce.crazyquiz.util.BasePresenter

class HighScoresPresenter(view: IHighScoresView) : BasePresenter<IHighScoresView>(view) {

    private val players = PlayersService()

    override fun onCreateView() {
        view.setScoresList(players.all())
    }
}