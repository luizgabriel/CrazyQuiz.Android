package br.edu.ifce.crazyquiz.screens.highscores

import android.content.Context
import br.edu.ifce.crazyquiz.data.Player
import br.edu.ifce.crazyquiz.util.SharedPreferencesStore
import com.google.gson.reflect.TypeToken

class PlayerStore(context: Context) : SharedPreferencesStore<Player>(context, object : TypeToken<ArrayList<Player>>() {}), IPlayerStore {

    private val lastPlayerPrefKey = "lastPlayerName"

    override fun getLastPlayerName(): String {
        return sharedPreferences.getString(lastPlayerPrefKey, "")
    }

    override fun add(player: Player) {
        val lastScoredPlayer = cache.find({ p -> p.name == player.name })

        if (lastScoredPlayer != null) {
            if (lastScoredPlayer.scores < player.scores)
                lastScoredPlayer.scores = player.scores
        } else {
            sharedPreferences.edit().putString(lastPlayerPrefKey, player.name).apply()
            cache.add(player)
        }

        saveChanges()
    }
}