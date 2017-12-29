package br.edu.ifce.crazyquiz.screens.highscores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.crazyquiz.R
import br.edu.ifce.crazyquiz.data.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_high_scores.*
import kotlinx.android.synthetic.main.high_scores_list_item.*

class HighScoresActivity : AppCompatActivity(), IHighScoresView {

    private val presenter = HighScoresPresenter(this, PlayerStore(applicationContext))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        highScoresList.setHasFixedSize(true)
        highScoresList.layoutManager = LinearLayoutManager(this)

        presenter.onCreateView()
    }

    override fun setScoresList(players: List<Player>) {
        val adapter = HighScoresListAdapter(players)
        highScoresList.adapter = adapter
    }

    class HighScoresListAdapter(private val players: List<Player>) : RecyclerView.Adapter<HighScoresListAdapter.ViewHolder>() {

        class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            fun bind(player: Player) {
                highScoresName.text = player.name
                highScoresValue.text = player.scores.toString()
                highScoresPlace.text = """${adapterPosition + 1}º"""
            }
        }

        override fun getItemCount() = players.count()

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val player = players[position]
            holder?.bind(player)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.high_scores_list_item, parent, false)
            return ViewHolder(view)
        }
    }


}

