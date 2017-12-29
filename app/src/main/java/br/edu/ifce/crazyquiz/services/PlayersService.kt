package br.edu.ifce.crazyquiz.services

import br.edu.ifce.crazyquiz.data.Player
import kotlin.coroutines.experimental.buildSequence

class PlayersService() {

    private val players: MutableList<Player>

    init {
        players = loadPlayers().toMutableList()
    }

    fun save(player: Player) {
        players.clear()
        players.add(player);
    }

    fun all(): List<Player> {
        return players.toList()
    }

    private fun loadPlayers(): Sequence<Player> = buildSequence<Player> {
        /*val players = orm.find(Player(), PultusORMCondition.Builder()
                .sort("scores", PultusORMQuery.Sort.DESCENDING)
                .build()
        )*/

        for (i in 1..100) {
            val scores = ((1000 / i) + (Math.random() % 999)).toInt()
            yield(Player("Teste " + i.toString(), scores))
        }

    }

}