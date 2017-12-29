package br.edu.ifce.crazyquiz.screens.highscores

import android.content.Context
import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.util.SharedPreferencesStore

class PlayerStore(context: Context) : SharedPreferencesStore<Player>(context, "players"), IPlayerStore {

    override fun add(player: Player) {
        val lastScoredPlayer = cache.find({ p -> p.name == player.name })

        if (lastScoredPlayer != null)
            if (lastScoredPlayer.scores < player.scores)
                lastScoredPlayer.scores = player.scores
        else
            cache.add(player)

        saveChanges()
    }
}