package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player

interface IHighScoresView {
    fun setScoresList(players: List<Player>)
}