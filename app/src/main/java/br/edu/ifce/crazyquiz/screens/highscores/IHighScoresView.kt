package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.data.QuestionnaireSession

interface IHighScoresView {
    fun setScoresList(players: List<Player>)
    fun showGameFinishedDialog(player: Player, mode: QuestionnaireSession.FinishedGameMode)
}