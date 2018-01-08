package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player

interface IPlayerStore : Iterable<Player> {
    fun add(player: Player)
    fun getLastPlayerName(): String
}