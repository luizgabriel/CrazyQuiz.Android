package br.edu.ifce.crazyquiz.screens.highscores

import br.edu.ifce.crazyquiz.data.Player

/**
 * Created by luizg on 29/12/2017.
 */
interface IPlayerStore : Iterable<Player> {
    fun add(player: Player)
}